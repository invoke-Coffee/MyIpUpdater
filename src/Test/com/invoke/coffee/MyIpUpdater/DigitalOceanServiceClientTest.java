package com.invoke.coffee.MyIpUpdater;

import com.myjeeva.digitalocean.pojo.DomainRecord;

import static org.mockito.Mockito.verify;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.mock.*;
import org.mockito.stubbing.OngoingStubbing;

import junit.framework.Assert;

class DigitalOceanServiceClientTest {
    DigitalOceanServiceClient sutClient;
    DomainRecord mockDomain;

    DigitalOcean mockApiClient;

    @BeforeEach
    void init() {
        mockDomain = Mockito.mock(DomainRecord.class);
        mockApiClient = Mockito.mock(DigitalOcean.class);
        sutClient = new DigitalOceanServiceClient("Test.Domain", "127.0.0.1", mockDomain, mockApiClient);
    }

    @Test
    @DisplayName("Assert that when record and MyIP match we return True")
    void checkTestTrue() {
        Mockito.when(mockDomain.getData()).thenReturn("127.0.0.1");
        Assert.assertEquals(true, sutClient.check());
    }

    @Test
    @DisplayName("Assert that when record and MyIP don't match we return False")
    void checkTestFalse() {
        Mockito.when(mockDomain.getData()).thenReturn("127.0.0.2");
        Assert.assertEquals(false, sutClient.check());
    }

    @Test
    @DisplayName("Assert that getRecordValue returns domain Record")
    void GetRecordValueTest() {
        Mockito.when(mockDomain.getData()).thenReturn("127.0.0.1");
        Assert.assertEquals("127.0.0.1", sutClient.getRecordValue());
    }

    @Test
    @DisplayName("Assert that UpdateRecord Calls SetData with myIp")
    void UpdateRecordTest() throws DigitalOceanException, RequestUnsuccessfulException {
        Mockito.when(mockDomain.getId()).thenReturn(1);

        sutClient.updateRecord();
        verify(mockApiClient).updateDomainRecord("Domain", 1, mockDomain);
    }
}
