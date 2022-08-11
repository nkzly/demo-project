package org.example.demo.demoproject.repository;

import org.example.demo.demoproject.TestUtil;
import org.example.demo.demoproject.model.CoinDetailInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CoinRepositoryTest extends TestUtil {

    private CoinRepository repository;


    @BeforeEach
    public void setup() {
        repository = new CoinRepository();
    }
    @Test
    void filterListAndChangeNameToUpperCase() {
        assertEquals(repository.filterListAndChangeNameToUpperCase(Arrays.asList(createCoin())).get(0).getName(), "BITCOIN");
    }

}