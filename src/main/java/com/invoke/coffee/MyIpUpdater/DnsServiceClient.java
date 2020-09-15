package com.invoke.coffee.MyIpUpdater;

public abstract class DnsServiceClient {
    private String dnsRecord;
    private String myIp;

    public abstract boolean check();

    public abstract String getRecordValue();

    public abstract void updateRecord();
}
