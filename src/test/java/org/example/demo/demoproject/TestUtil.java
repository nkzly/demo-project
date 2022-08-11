package org.example.demo.demoproject;

import org.example.demo.demoproject.model.CoinDetailInfo;

public abstract class TestUtil {
    public CoinDetailInfo createCoin() {
        CoinDetailInfo info = new CoinDetailInfo();
        info.setName("BITCOIN");
        info.setSymbol("BTC");
        info.setId("123");
        info.setPriceUsd("40000.00");
        return info;
    }
}
