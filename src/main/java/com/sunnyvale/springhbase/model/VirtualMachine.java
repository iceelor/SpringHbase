package com.sunnyvale.springhbase.model;

public class VirtualMachine {
	private int hashCode;
	private long dataTime;
	private int activeThreadCount;
	private long gc1Count;
	private long gc1Time;
	private long gc2Count;
	private long gc2Time;
	private long heapUsed;
	private long heapCommitted;
	private long nonHeapUsed;
	private long nonHeapCommitted;
	private double processCPUTime;

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public long getDataTime() {
		return dataTime;
	}

	public void setDataTime(long dataTime) {
		this.dataTime = dataTime;
	}

	public int getActiveThreadCount() {
		return activeThreadCount;
	}

	public void setActiveThreadCount(int activeThreadCount) {
		this.activeThreadCount = activeThreadCount;
	}

	public long getGc1Count() {
		return gc1Count;
	}

	public void setGc1Count(long gc1Count) {
		this.gc1Count = gc1Count;
	}

	public long getGc1Time() {
		return gc1Time;
	}

	public void setGc1Time(long gc1Time) {
		this.gc1Time = gc1Time;
	}

	public long getGc2Count() {
		return gc2Count;
	}

	public void setGc2Count(long gc2Count) {
		this.gc2Count = gc2Count;
	}

	public long getGc2Time() {
		return gc2Time;
	}

	public void setGc2Time(long gc2Time) {
		this.gc2Time = gc2Time;
	}

	public long getHeapUsed() {
		return heapUsed;
	}

	public void setHeapUsed(long heapUsed) {
		this.heapUsed = heapUsed;
	}

	public long getHeapCommitted() {
		return heapCommitted;
	}

	public void setHeapCommitted(long heapCommitted) {
		this.heapCommitted = heapCommitted;
	}

	public long getNonHeapUsed() {
		return nonHeapUsed;
	}

	public void setNonHeapUsed(long nonHeapUsed) {
		this.nonHeapUsed = nonHeapUsed;
	}

	public long getNonHeapCommitted() {
		return nonHeapCommitted;
	}

	public void setNonHeapCommitted(long nonHeapCommitted) {
		this.nonHeapCommitted = nonHeapCommitted;
	}

	public double getProcessCPUTime() {
		return processCPUTime;
	}

	public void setProcessCPUTime(double processCPUTime) {
		this.processCPUTime = processCPUTime;
	}

	@Override
	public String toString() {
		return "VirtualMachine [hashCode=" + hashCode + ", dataTime=" + dataTime + ", activeThreadCount=" + activeThreadCount + ", gc1Count=" + gc1Count + ", gc1Time=" + gc1Time + ", gc2Count="
				+ gc2Count + ", gc2Time=" + gc2Time + ", heapUsed=" + heapUsed + ", heapCommitted=" + heapCommitted + ", nonHeapUsed=" + nonHeapUsed + ", nonHeapCommitted=" + nonHeapCommitted
				+ ", processCPUTime=" + processCPUTime + "]";
	}

}
