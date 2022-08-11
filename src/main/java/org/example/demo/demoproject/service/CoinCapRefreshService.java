package org.example.demo.demoproject.service;

import org.example.demo.demoproject.config.Configuration;

public interface CoinCapRefreshService {
    int refresh() throws Exception;

    void setConfig(Configuration config);
}
