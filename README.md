# MyIpUpdater

This tool is used to check for you current public IP address and if needed update an A record to reflect this.

See [Using MyIpUpdater to update dynamic public Addresses](https://www.invoke.coffee/using-myipupdater-to-update-dynamic-public-addresses.html) for a more detailed user Guide.

Currently this only support ipv4 (A records)

This supports the following DNS providers
* Digital ocean - do keyword

## Status

![Java CI with Maven](https://github.com/invoke-Coffee/MyIpUpdater/workflows/Java%20CI%20with%20Maven/badge.svg)

### Building

This uses Maven

``` sh
mvn install
mvn clean compile assembly:single
```

### Running

The Command line syntax is domainName DnsHost Authdata

For example

``` sh
java -cp target/MyIpUpdater-jar-with-dependencies.jar com.invoke.coffee.MyIpUpdater.main test.home.invoke.coffee do xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
````
