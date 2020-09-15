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
        DomainRecords domainInfo = apiClient.getDomainRecords(domainName, 1, 100);
        for (DomainRecord domain : domainInfo.getDomainRecords()) {
            if (domain.getName().matches(hostName)) {
                this.domainInfo = domain;
            }

        }

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
        } catch (DigitalOceanException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RequestUnsuccessfulException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
