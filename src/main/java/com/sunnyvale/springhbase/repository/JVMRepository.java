package com.sunnyvale.springhbase.repository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Repository;

import com.sunnyvale.springhbase.model.Server;
import com.sunnyvale.springhbase.model.VirtualMachine;
import com.sunnyvale.springhbase.util.ByteUtils;

@Repository
public class JVMRepository {

	@Autowired
	private HbaseTemplate hbaseTemplate;

	private String tableName = "JVM_";

	byte[] fActiveThreadCount = Bytes.toBytes("activeThreadCount");
	byte[] fGc1Count = Bytes.toBytes("gc1Count");
	byte[] fGc1Time = Bytes.toBytes("gc1Time");
	byte[] fGc2Count = Bytes.toBytes("gc2Count");
	byte[] fGc2Time = Bytes.toBytes("gc2Time");
	byte[] fHeapUsed = Bytes.toBytes("heapUsed");
	byte[] fHeapCommitted = Bytes.toBytes("heapCommitted");
	byte[] fNonHeapUsed = Bytes.toBytes("nonHeapUsed");
	byte[] fNonHeapCommitted = Bytes.toBytes("nonHeapCommitted");
	byte[] fProcessCPUTime = Bytes.toBytes("processCPUTime");

	private static final Logger logger = LoggerFactory.getLogger(JVMRepository.class);

	// get current JVM Status
	public VirtualMachine getCurrentJVM(final int hashCode) throws IOException {
		String jvmTableName = tableName + Integer.toString(hashCode);
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd");

		long current = System.currentTimeMillis();
		long period = 5000;

		String currentDate = df.format(new Date(current));

		final byte[] key = Bytes.toBytes(currentDate);
		Scan scan = new Scan();

		scan.setTimeRange(current - period, current);

		Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(key));

		scan.setFilter(filter);

		
		VirtualMachine jvm = hbaseTemplate.find(jvmTableName, scan, new ResultsExtractor<VirtualMachine>() {
			public VirtualMachine extractData(ResultScanner results) throws Exception {
				for (Result result : results) {
					VirtualMachine vm = new VirtualMachine();

					Map<byte[], byte[]> map = result.getFamilyMap(fGc1Count);
					Set<byte[]> set = map.keySet();

					for (byte[] qualifier : set) {
						vm.setHashCode(hashCode);
						vm.setGc1Count(ByteUtils.stringToLong(result.getValue(fGc1Count, qualifier)));
						vm.setGc1Time(ByteUtils.stringToLong(result.getValue(fGc1Time, qualifier)));
						vm.setGc2Count(ByteUtils.stringToLong(result.getValue(fGc2Count, qualifier)));
						vm.setGc2Time(ByteUtils.stringToLong(result.getValue(fGc2Time, qualifier)));
						vm.setHeapUsed(ByteUtils.stringToLong(result.getValue(fHeapUsed, qualifier)));
						vm.setHeapCommitted(ByteUtils.stringToLong(result.getValue(fHeapCommitted, qualifier)));
						vm.setNonHeapUsed(ByteUtils.stringToLong(result.getValue(fNonHeapUsed, qualifier)));
						vm.setNonHeapCommitted(ByteUtils.stringToLong(result.getValue(fNonHeapCommitted, qualifier)));
						vm.setProcessCPUTime(ByteUtils.stringToInt(result.getValue(fProcessCPUTime, qualifier)));
						
						System.out.println("##### " + vm.toString());
					}
					return vm;
				}

				return null;
			}
		});

		return jvm;
	}

	public List<VirtualMachine> getJVMList(final int hashCode, long timeStamp, long period) throws IOException {
		String jvmTableName = tableName + Integer.toString(hashCode);

		DateFormat df = new SimpleDateFormat("yyyy_MM_dd");

		String dateStamp = df.format(new Date(timeStamp));

		final byte[] key = Bytes.toBytes(dateStamp);

		Scan scan = new Scan();

		scan.setTimeRange(timeStamp - period, timeStamp);

		Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(key));

		scan.setFilter(filter);

		List<VirtualMachine> list = hbaseTemplate.find(jvmTableName, scan, new RowMapper<VirtualMachine>() {

			@Override
			public VirtualMachine mapRow(Result result, int rowNum) throws Exception {
				VirtualMachine vm = new VirtualMachine();

				Map<byte[], byte[]> map = result.getFamilyMap(fGc1Count);
				Set<byte[]> set = map.keySet();

				for (byte[] qualifier : set) {
					vm.setHashCode(hashCode);
					vm.setGc1Count(ByteUtils.stringToLong(result.getValue(fGc1Count, qualifier)));
					vm.setGc1Time(ByteUtils.stringToLong(result.getValue(fGc1Time, qualifier)));
					vm.setGc2Count(ByteUtils.stringToLong(result.getValue(fGc2Count, qualifier)));
					vm.setGc2Time(ByteUtils.stringToLong(result.getValue(fGc2Time, qualifier)));
					vm.setHeapUsed(ByteUtils.stringToLong(result.getValue(fHeapUsed, qualifier)));
					vm.setHeapCommitted(ByteUtils.stringToLong(result.getValue(fHeapCommitted, qualifier)));
					vm.setNonHeapUsed(ByteUtils.stringToLong(result.getValue(fNonHeapUsed, qualifier)));
					vm.setNonHeapCommitted(ByteUtils.stringToLong(result.getValue(fNonHeapCommitted, qualifier)));
					vm.setProcessCPUTime(ByteUtils.stringToInt(result.getValue(fProcessCPUTime, qualifier)));
				}

				return vm;
			}

		});

		return list;
	}
}
