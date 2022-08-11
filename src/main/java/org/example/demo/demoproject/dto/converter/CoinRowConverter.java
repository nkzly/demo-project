package org.example.demo.demoproject.dto.converter;

import org.example.demo.demoproject.model.CoinDetailInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinRowConverter implements RowMapper<CoinDetailInfo> {

    @Override
    public CoinDetailInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CoinDetailInfo coinDetailInfo= new CoinDetailInfo();
        coinDetailInfo.setId(rs.getString("id"));
        coinDetailInfo.setSymbol(rs.getString("symbol"));
        coinDetailInfo.setName(rs.getString("name"));
        coinDetailInfo.setPriceUsd(rs.getString("priceUsd"));
        coinDetailInfo.setExplorer(rs.getString("explorer"));
        coinDetailInfo.setChangePercent24Hr(rs.getString("changePercent24Hr"));
        coinDetailInfo.setMarketCapUsd(rs.getString("marketCapUsd"));
        coinDetailInfo.setMaxSupply(rs.getString("maxSupply"));
        coinDetailInfo.setRank(rs.getString("rank"));
        coinDetailInfo.setSupply(rs.getString("supply"));
        coinDetailInfo.setVolumeUsd24Hr("volumeUsd24Hr");
        coinDetailInfo.setVwap24Hr("vwap24Hr");
        return coinDetailInfo;
    }
}

