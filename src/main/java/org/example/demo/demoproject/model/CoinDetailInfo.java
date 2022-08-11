package org.example.demo.demoproject.model;

import lombok.Data;

@Data
public class CoinDetailInfo {
    public String id;
    public String rank;
    public String symbol;
    public String name;
    public String supply;
    public String maxSupply;
    public String marketCapUsd;
    public String volumeUsd24Hr;
    public String priceUsd;
    public String changePercent24Hr;
    public String vwap24Hr;
    public String explorer;
}
