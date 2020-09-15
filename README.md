# MyIpUpdater
Monitor your public IP address and update



### Building

This uses Maven

```
mvn install
mvn clean compile assembly:single
```

### Running

The Command line syntax is domainName DnsHost Authdata

For example

```
java -cp target/MyIpUpdater-jar-with-dependencies.jar com.invoke.coffee.MyIpUpdater.main test.home.invoke.coffee do xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
````