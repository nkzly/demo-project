package org.example.demo.demoproject.controller;

import org.example.demo.demoproject.service.CoinCapRefreshServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
class CoinCapRefreshControllerTest {

    @Mock
    private CoinCapRefreshServiceImpl refresherService;
    private CoinCapRefreshController controller;
    @BeforeEach
    void setUp() {
        controller = new CoinCapRefreshController(refresherService);
    }

    @Test
    void refreshCoinInfo() throws Exception {
        when(refresherService.refresh()).thenReturn(10);
        assertEquals(controller.refreshCoinInfo(), ResponseEntity.ok(true));
        verify(refresherService, times(1)).refresh();
    }
}