package com.sunnyvale.springhbase.util;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunnyvale.springhbase.repository.UserRepository;

@Component
public class UserUtils implements InitializingBean {

	private String tableName = "users";
	private byte[] tableNameAsBytes = Bytes.toBytes("users");

	@Resource(name = "hbaseConfiguration")
	private Configuration config;

	@Autowired
	private UserRepository userRepository;

	private HBaseAdmin admin;

	public void initialize() throws IOException {

		if (admin.tableExists(tableNameAsBytes)) {
			if (!admin.isTableDisabled(tableNameAsBytes)) {
				System.out.printf("Disabling %s\n", tableName);
				admin.disableTable(tableNameAsBytes);
			}
			System.out.printf("Deleting %s\n", tableName);
			admin.deleteTable(tableNameAsBytes);
		}
		HTable table = new HTable(config, tableNameAsBytes);
		HTableDescriptor tableDescriptor = new HTableDescriptor(table.getName());
		HColumnDescriptor columnDescriptor = new HColumnDescriptor(UserRepository.CF_INFO);
		tableDescriptor.addFamily(columnDescriptor);

		admin.createTable(tableDescriptor);
		table.close();
	}

	public void addUsers() {
		userRepository.save("admin", "admin@mail.com", "12345");
		userRepository.save("super", "super@mail.com", "abcde");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		admin = new HBaseAdmin(config);
	}

}
