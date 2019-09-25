package com.t1708e.blockchain.repository;

import com.t1708e.blockchain.entity.Coin;
import com.t1708e.blockchain.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketRepository extends JpaRepository<Market, String>, JpaSpecificationExecutor<Market> {
}
