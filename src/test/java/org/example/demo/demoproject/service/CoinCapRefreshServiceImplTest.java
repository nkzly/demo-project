package org.example.demo.demoproject.service;

import org.example.demo.demoproject.TestUtil;
import org.example.demo.demoproject.config.Configuration;
import org.example.demo.demoproject.model.CoinDetailInfo;
import org.example.demo.demoproject.model.Coins;
import org.example.demo.demoproject.repository.CoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
class CoinCapRefreshServiceImplTest extends TestUtil {

    private CoinCapRefreshServiceImpl service;
    @Mock
    private CoinRepository repository;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        service = new CoinCapRefreshServiceImpl(restTemplate, repository);
        Configuration configuration= new Configuration();
        configuration.setCoinCapUrl("https://api.coincap.io/v2/assets");
        service.setConfig(configuration);
    }

    @Test
    void refresh() throws Exception {
        Coins coins= new Coins();
        ArrayList<CoinDetailInfo> coinlist = new ArrayList<>();
        coinlist.add(createCoin());

        coins.setData(coinlist);
        ResponseEntity<Coins> myEntity = new ResponseEntity<>(coins, HttpStatus.ACCEPTED);
        myEntity.getBody().getData().add(createCoin());
        when(restTemplate.exchange(
                ArgumentMatchers.any(URI.class),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<Coins>>any())
        ).thenReturn(myEntity);

        service.refresh();
        verify(repository, times(1)).deleteRecords();
    }

}