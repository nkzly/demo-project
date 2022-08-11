package org.example.demo.demoproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CoinService {
    ResponseEntity getCoins();

    ResponseEntity deleteCoin(@PathVariable(value = "id") String coinId);
}
