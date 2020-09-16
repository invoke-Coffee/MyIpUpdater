package com.invoke.coffee.MyIpUpdater;

import java.io.IOException;

import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;

/**
 * Main function to parse the ARgs run the update
 *
 */
public class main {
    public static void main(String[] args) throws DigitalOceanException, RequestUnsuccessfulException {
        String myIp = "";
        String myDomain = args[0];
        String domainHost = args[1];
        String myToken = args[2];
        DnsServiceClient myDomainClient = null;
        try {
            myIp = MyIp.get();
            if (domainHost.matches("do")) {
                myDomainClient = new DigitalOceanServiceClient(myDomain, myIp, myToken);
            } else {
                System.out.println("No valid domain Host specified");
                System.exit(1);
            }
            System.out.println("My current IP address is " + myIp);
            System.out.println("Domain IP is " + myDomainClient.getRecordValue());

            if (!(myDomainClient.check())) {
                System.out.println("Updating A record");
                myDomainClient.updateRecord();
            } else {
                System.out.println("Record is up to date");
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
