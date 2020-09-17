package com.invoke.coffee.MyIpUpdater;

import java.io.IOException;

import com.invoke.coffee.MyIpUpdater.MyIp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class MyIpTest{
    @Test
    @DisplayName("Assert that testGet returns a valid IP")
    void testGet() throws IOException{
        String sutReturn = MyIp.get();
        Assert.assertEquals(true, sutReturn.matches("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$"));

    }
}