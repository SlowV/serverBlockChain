package com.t1708e.blockchain.service;

import com.t1708e.blockchain.entity.Coin;
import com.t1708e.blockchain.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CoinService {
    @Autowired
    private CoinRepository coinRepository;

    public Page<Coin> getAll(Specification specification, int page, int limit) {
        return coinRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Coin getById(String id) {
        return coinRepository.findById(id).orElse(null);
    }

    public Coin create(Coin coin) {
        return coinRepository.save(coin);
    }

    public Coin update(Coin coin) {
        coin.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return coinRepository.save(coin);
    }

    public boolean delete(Coin coin) {
        coin.setStatus(-1);
        coinRepository.save(coin);
        return true;
    }

}
