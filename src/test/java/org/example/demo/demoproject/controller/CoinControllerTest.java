package org.example.demo.demoproject.controller;

import org.example.demo.demoproject.TestUtil;
import org.example.demo.demoproject.exception.ResourceNotFoundException;
import org.example.demo.demoproject.service.CoinCapRefreshServiceImpl;
import org.example.demo.demoproject.service.CoinServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
class CoinControllerTest extends TestUtil {


    @Mock
    private CoinServiceImpl coinService;
    private CoinController controller;

    @BeforeEach
    void setUp() {
        controller = new CoinController(coinService);
    }

    @Test
    void getCoins() {
        when(coinService.getCoins()).thenReturn(ResponseEntity.ok().body(Arrays.asList(createCoin())));
        assertEquals(controller.getCoins(), ResponseEntity.ok().body(Arrays.asList(createCoin())));
        verify(coinService, times(1)).getCoins();
    }

    @Test
    void deleteCoin() throws ResourceNotFoundException {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        when(coinService.deleteCoin(anyString())).thenReturn(ResponseEntity.ok().body(response));
        assertEquals(controller.deleteCoin("123"), ResponseEntity.ok().body(response));
        verify(coinService, times(1)).deleteCoin("123");
    }
}