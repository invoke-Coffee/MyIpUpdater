package com.invoke.coffee.MyIpUpdater;

public abstract class DnsServiceClient {
    private String dnsRecord;
    private String myIp;

    /*
     * This method should check if myIp is == Current Record Value
     */
    public abstract boolean check();

    /*
     * This method should return the current value of this A record
     */
    public abstract String getRecordValue();

    /*
     * This method should update the A record with myIp
     */
    public abstract void updateRecord();
}
