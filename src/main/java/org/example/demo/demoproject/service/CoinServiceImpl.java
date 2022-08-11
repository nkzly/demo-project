package org.example.demo.demoproject.service;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.demoproject.exception.ResourceNotFoundException;
import org.example.demo.demoproject.repository.CoinRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CoinServiceImpl extends CommonService implements CoinService {

    private CoinRepository repository;

    public CoinServiceImpl(CoinRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity getCoins() {
        try {
            return ResponseEntity.ok().body(repository.findAll());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity deleteCoin(@PathVariable(value = "id") String coinId) {
        try {
            repository.findById(coinId)
                    .orElseThrow(() -> new ResourceNotFoundException("Coin not found for this id :: " + coinId));

            repository.delete(coinId);

            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            log.info(coinId + ": deleted");
            return ResponseEntity.ok().body(response);
        } catch (ResourceNotFoundException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
