package org.example.config;

import org.aeonbits.owner.Config;

import org.aeonbits.owner.Config.Key;

@Config.Sources("classpath:config.properties")
public interface TestConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("api.url")
    String apiUrl();

    @Key("timeout")
    long timeout();

    @Key("logging.mode")
    String loggingMode();

    @Key("admin.username")
    String adminUsername();

    @Key("admin.password")
    String adminPassword();

    @Key("product.name")
    String productName();

    @Key("product.price")
    int productPrice();
}