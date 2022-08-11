package org.example.demo.demoproject.service;

import org.example.demo.demoproject.TestUtil;
import org.example.demo.demoproject.model.CoinDetailInfo;
import org.example.demo.demoproject.repository.CoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
class CoinServiceImplTest extends TestUtil {

    private CoinServiceImpl service;
    private CoinRepository repository;

    @BeforeEach
    public void setup() {
        repository = mock(CoinRepository.class);
        service = new CoinServiceImpl(repository);
    }

    @Test
    void getCoins() { List<CoinDetailInfo> coinDetailInfoList = new ArrayList<>();
        CoinDetailInfo info = createCoin();
        coinDetailInfoList.add(info);

        when(repository.findAll()).thenReturn(coinDetailInfoList);
        assertEquals(service.getCoins().getBody(), coinDetailInfoList);
    }

    @Test
    void deleteCoin() {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        when(repository.findById(anyString())).thenReturn(Optional.of(createCoin()));
        when(repository.delete(anyString())).thenReturn(1);
        assertEquals(service.deleteCoin("123").getBody(), response);
    }


    @Test
    public void whenExceptionThrown_deleteCoin() {
        assertEquals(ResponseEntity.notFound().build(), service.deleteCoin("123"));
    }

}