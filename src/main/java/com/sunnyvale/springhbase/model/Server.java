package com.sunnyvale.springhbase.model;

public class Server {
	private int hashCode;
	private String rowNames;
	private String ip;
	private int ports;
	private boolean isRunning;
	private String serviceName;
	private String serverGroupName;
	private String instanceName;
	
	public Server(int hashCode, String rowNames, String ip, int ports, boolean isRunning, String serviceName, String serverGroupName, String instanceName) {
		this.hashCode = hashCode;
		this.rowNames = rowNames;
		this.ip = ip;
		this.ports = ports;
		this.isRunning = isRunning;
		this.serviceName = serviceName;
		this.serverGroupName = serverGroupName;
		this.instanceName = instanceName;
	}

	public Server() {
		this.hashCode = 0;
		this.rowNames = "";
		this.ip = "";
		this.ports = 0;
		this.isRunning = false;
		this.serviceName = "";
		this.serverGroupName = "";
		this.instanceName = "";
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public String getRowNames() {
		return rowNames;
	}

	public void setRowNames(String rowNames) {
		this.rowNames = rowNames;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPorts() {
		return ports;
	}

	public void setPorts(int ports) {
		this.ports = ports;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServerGroupName() {
		return serverGroupName;
	}

	public void setServerGroupName(String serverGroupName) {
		this.serverGroupName = serverGroupName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	@Override
	public String toString() {
		return "Server [hashCode=" + hashCode + ", rowNames=" + rowNames + ", ip=" + ip + ", ports=" + ports + ", isRunning=" + isRunning + ", serviceName=" + serviceName + ", serverGroupName="
				+ serverGroupName + ", instanceName=" + instanceName + "]";
	}
	
}
