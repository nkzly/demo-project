package org.example.demo.demoproject.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.demo.demoproject.service.CoinCapRefreshService;
import org.example.demo.demoproject.service.CoinCapRefreshServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CoinCapRefreshController {


    private final CoinCapRefreshService refresherService;

    public CoinCapRefreshController( @Lazy CoinCapRefreshService refresherService) {
        this.refresherService = refresherService;
    }

    @Scheduled(fixedRate = 50000)
    public ResponseEntity<Boolean> refreshCoinInfo() throws Exception {
        try {
            refresherService.refresh();
            log.info("refreshed");
        } catch (Exception e) {
            log.error("refresh error:" + e.getMessage());
            throw e;
        }
        return ResponseEntity.ok(true);
    }
}
