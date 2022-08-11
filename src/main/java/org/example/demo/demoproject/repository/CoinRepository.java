package org.example.demo.demoproject.repository;

import org.example.demo.demoproject.dto.converter.CoinRowConverter;
import org.example.demo.demoproject.model.CoinDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CoinRepository {
    @Autowired
    JdbcTemplate jdbctemplate;

    public int[] insertCoins(List<CoinDetailInfo> coins) {
        List<CoinDetailInfo> filteredCoins = filterListAndChangeNameToUpperCase(coins);
        return jdbctemplate.batchUpdate("insert into COIN (id, symbol, name, priceUsd, explorer, changePercent24Hr, marketCapUsd," +
                        " maxSupply,rank,supply,volumeUsd24Hr,vwap24Hr) "
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        prepareStatementForCoins(ps, i, filteredCoins);
                    }
                    @Override
                    public int getBatchSize() {
                        return filteredCoins.size();
                    }
                });
    }

    protected List<CoinDetailInfo> filterListAndChangeNameToUpperCase(List<CoinDetailInfo> coins) {
        List<CoinDetailInfo> filteredCoins = coins.stream().limit(10).map(c -> {
            c.setName(c.getName().toUpperCase());
            return c;
        }).collect(Collectors.toList());
        return filteredCoins;
    }

    private void prepareStatementForCoins(PreparedStatement ps, int i, List<CoinDetailInfo> filteredCoins) throws SQLException {
        ps.setString(1, filteredCoins.get(i).getId());
        ps.setString(2, filteredCoins.get(i).getSymbol());
        ps.setString(3, filteredCoins.get(i).getName());
        ps.setString(4, filteredCoins.get(i).getPriceUsd());
        ps.setString(5, filteredCoins.get(i).getExplorer());
        ps.setString(6, filteredCoins.get(i).getChangePercent24Hr());
        ps.setString(7, filteredCoins.get(i).getMarketCapUsd());
        ps.setString(8, filteredCoins.get(i).getMaxSupply());
        ps.setString(9, filteredCoins.get(i).getRank());
        ps.setString(10, filteredCoins.get(i).getSupply());
        ps.setString(11, filteredCoins.get(i).getVolumeUsd24Hr());
        ps.setString(12, filteredCoins.get(i).getVwap24Hr());
    }

    public int deleteRecords() {
        return jdbctemplate.update("delete from COIN");
    }

    public List<CoinDetailInfo> findAll() {
        return jdbctemplate.query("select * from COIN ", new CoinRowConverter());
    }

    public Optional<CoinDetailInfo> findById(String coinId) {
        return Optional.ofNullable(jdbctemplate.queryForObject("select * from COIN where id=?",
                new Object[]{coinId}, new CoinRowConverter()));
    }

    public int delete(String id) {
        return jdbctemplate.update("delete from COIN where id = ?", id);
    }
}
