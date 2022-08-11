package org.example.demo.demoproject.service;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.demoproject.config.Configuration;
import org.example.demo.demoproject.exception.NotFoundException;
import org.example.demo.demoproject.model.Coins;
import org.example.demo.demoproject.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class CoinCapRefreshServiceImpl extends CommonService implements CoinCapRefreshService {

    @Autowired
    private Configuration config;

    private final RestTemplate restTemplate;
    private final CoinRepository repository;

    public CoinCapRefreshServiceImpl(@Lazy RestTemplate restTemplate, CoinRepository repository) {
        this.restTemplate =restTemplate;
        this.repository=repository;
    }

    @Override
    public int refresh() throws Exception {
        final String baseUrl = config.getCoinCapUrl(); //"https://api.coincap.io/v2/assets";
        URI uri = new URI(baseUrl);
        ResponseEntity<Coins> result = restTemplate.exchange(uri, HttpMethod.GET, getHeader(), Coins.class);
        if (result == null || result.getBody() == null) {
            log.error("coinCap not return Data");
            throw new NotFoundException();
        }
        deleteAllrecords();
        insertRecords(result);
        return result.getBody().getData().size();
    }

    private void insertRecords(ResponseEntity<Coins> result) {
        repository.insertCoins(result.getBody().getData());
        log.info("records inserted");
    }

    private void deleteAllrecords() {
        repository.deleteRecords();
        log.info("records deleted for ready to refresh insert");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void setConfig(Configuration config) {
        this.config = config;
    }

}
