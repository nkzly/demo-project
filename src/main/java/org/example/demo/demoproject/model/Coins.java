package org.example.demo.demoproject.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Coins {
    public ArrayList<CoinDetailInfo> data;
    public long timestamp;
}
