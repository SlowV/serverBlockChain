package com.t1708e.blockchain.dto;

import com.t1708e.blockchain.entity.Coin;
import com.t1708e.blockchain.entity.Market;
import com.t1708e.blockchain.util.DateTimeUtil;
import com.t1708e.blockchain.util.ObjectUtil;

public class CoinDTO {
    private String id;
    private String name;
    private String baseAsset;
    private String quoteAsset;
    private String lastPrice;
    private String volumn24h;
    private String marketName;
    private String marketIdString;
    private String createdAt;
    private String updatedAt;
    private String status;

    public CoinDTO(Coin coin) {
        ObjectUtil.cloneObject(this, coin);
//        this.marketName = coin.getMarket().getName();
        this.createdAt = DateTimeUtil.formatDateFromLong(coin.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(coin.getUpdatedAt());
        this.status = coin.getStatus() == 1 ? "Active" : "Deactive";
    }

    public String getId() {
        return id;
    }

    public String getMarketIdString() {
        return marketIdString;
    }

    public void setMarketIdString(String marketIdString) {
        this.marketIdString = marketIdString;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getVolumn24h() {
        return volumn24h;
    }

    public void setVolumn24h(String volumn24h) {
        this.volumn24h = volumn24h;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
