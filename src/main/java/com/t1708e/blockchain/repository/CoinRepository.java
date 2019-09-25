package com.t1708e.blockchain.repository;

import com.t1708e.blockchain.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoinRepository extends JpaRepository<Coin, String>, JpaSpecificationExecutor<Coin> {
}
