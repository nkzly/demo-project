package org.example.demo.demoproject.controller;

import org.example.demo.demoproject.exception.ResourceNotFoundException;
import org.example.demo.demoproject.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/coins")
public class CoinController {
    private final CoinService coinService;


    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping(path = "/list")
    @ResponseBody
    @CrossOrigin(value = "*")
    public ResponseEntity getCoins() {
        return coinService.getCoins();
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity deleteCoin(@PathVariable(value = "id") String coinId) throws ResourceNotFoundException {
        return coinService.deleteCoin(coinId);
    }
}
