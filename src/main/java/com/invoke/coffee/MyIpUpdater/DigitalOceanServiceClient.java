package com.invoke.coffee.MyIpUpdater;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.DomainRecords;

public class DigitalOceanServiceClient extends DnsServiceClient {
    private String hostName;
    private String domainName;
    private String myIp;
    private DigitalOcean apiClient;
    private DomainRecord domainInfo;

    public DigitalOceanServiceClient(String dnsRecord, String myIp, String authToken)
            throws DigitalOceanException, RequestUnsuccessfulException {
        this.hostName = dnsRecord.split("\\.")[0];
        this.domainName = dnsRecord.replaceAll("^\\w*\\.", "");
        this.myIp = myIp;

        this.apiClient = new DigitalOceanClient("v2", authToken);
        // For this API we need to find the Record that we want, so we iterate over the
        // records in this zone
        DomainRecords domainInfo = apiClient.getDomainRecords(domainName, 1, 100);
        for (DomainRecord domain : domainInfo.getDomainRecords()) {
            if (domain.getName().matches(hostName)) {
                // We only need to retain the record we care about
                this.domainInfo = domain;
            }

        }

    }

    public DigitalOceanServiceClient(String dnsRecord, String myIp, DomainRecord domain, DigitalOcean apiClient){
        this.hostName = dnsRecord.split("\\.")[0];
        this.domainName = dnsRecord.replaceAll("^\\w*\\.", "");
        this.myIp = myIp;
        this.domainInfo = domain;
        this.apiClient = apiClient;

    }

    @Override
    public boolean check() {
        Boolean checkResult = null;
        if (this.getRecordValue().matches(myIp)) {
            checkResult = true;
        } else {
            checkResult = false;
        }

        return checkResult;
    }

    @Override
    public String getRecordValue() {
        return domainInfo.getData().toString();
    }

    @Override
    public void updateRecord() {
        domainInfo.setData(myIp);
        try {
            apiClient.updateDomainRecord(domainName, domainInfo.getId(), domainInfo);
        } catch (DigitalOceanException | RequestUnsuccessfulException e) {
            e.printStackTrace();
        }
    }

}
