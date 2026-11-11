package org.example.config;

import org.aeonbits.owner.ConfigFactory;

public final class ConfigProvider {

    public static final TestConfig CONFIG =
            ConfigFactory.create(TestConfig.class);

    private ConfigProvider() {
    }
}