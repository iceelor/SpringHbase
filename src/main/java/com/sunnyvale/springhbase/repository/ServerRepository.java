package com.sunnyvale.springhbase.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

import com.sunnyvale.springhbase.model.Server;
import com.sunnyvale.springhbase.util.ByteUtils;

@Repository
public class ServerRepository {

	@Autowired
	private HbaseTemplate hbaseTemplate;

	private String tableName = "Server";
	
	private byte[] qIp = Bytes.toBytes("ip");
	private byte[] qPorts = Bytes.toBytes("ports");
	private byte[] qIsRunning = Bytes.toBytes("isRunning");
	private byte[] qRowNames = Bytes.toBytes("rowNames");
	

	private static final Logger logger = LoggerFactory.getLogger(ServerRepository.class);
	
	public List<Server> getServerList() {
		return hbaseTemplate.find(tableName, "rowNames", new RowMapper<Server>() {
			@Override
			public Server mapRow(Result result, int rowNum) throws Exception {
				Server server = new Server();
				Map<byte[], byte[]> map = result.getFamilyMap(qRowNames);
				Collection<byte[]> col = map.values();
				for(byte[] val : col) {
					server.setHashCode(ByteUtils.stringToInt(val));
				}
				return server;
			}
		});

	}

	public Server getServer(final int hashCode) {
		final byte[] key = Bytes.toBytes(Integer.toString(hashCode));
		Scan scan = new Scan();

		Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(key));		
		scan.setFilter(filter);

		Server server = hbaseTemplate.find(tableName, scan, new ResultsExtractor<Server>(){
			public Server extractData(ResultScanner results) throws Exception {
				for(Result result : results) {
					Server s = new Server();
					s.setHashCode(hashCode);
					s.setIp(Bytes.toString(result.getValue(qIp, key)));
					s.setPorts(ByteUtils.stringToInt(result.getValue(qPorts, key)));
					s.setRunning(ByteUtils.stringToBoolean(result.getValue(qIsRunning, key)));
					return s;
				}
				
				return null;
			}
		});
		
		return server;

	}

}
