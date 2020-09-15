package com.invoke.coffee.MyIpUpdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MyIp {
    public static String get() throws IOException {
        String myIp = "";
        URL url = new URL("https://checkip.amazonaws.com/");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        myIp = br.readLine();

        return myIp;

    }
}
