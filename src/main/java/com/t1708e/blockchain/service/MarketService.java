package com.t1708e.blockchain.service;

import com.t1708e.blockchain.entity.Coin;
import com.t1708e.blockchain.entity.Market;
import com.t1708e.blockchain.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class MarketService {
    @Autowired
    private MarketRepository marketRepository;

    public Page<Market> getAll(Specification specification, int page, int limit) {
        return marketRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Market getById(String id) {
        return marketRepository.findById(id).orElse(null);
    }

    public Market create(Market market) {
        return marketRepository.save(market);
    }

    public Market update(Market market) {
        market.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return marketRepository.save(market);
    }

    public boolean delete(Market market) {
        market.setStatus(-1);
        marketRepository.save(market);
        return true;
    }
}
