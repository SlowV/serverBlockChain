package com.t1708e.blockchain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Coin {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String baseAsset;
    private String quoteAsset;
    private String lastPrice;
    private String volumn24h;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marketId")
    private Market market;
    private String marketIdString;
    private long createdAt;
    private long updatedAt;
    private int status;

    public Coin() {
        long now = Calendar.getInstance().getTimeInMillis();
        this.createdAt = now;
        this.updatedAt = now;
        this.status = 1;
    }

    public String getId() {
        return id;
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

    public Market getMarket() {
        return market;
    }

    public void setMarketId(Market market) {
        this.market = market;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMarketIdString() {
        return marketIdString;
    }

    public void setMarketIdString(String marketIdString) {
        this.marketIdString = marketIdString;
    }

    public static final class CoinBuilder {
        private String name;
        private String baseAsset;
        private String quoteAsset;
        private String lastPrice;
        private String volumn24h;
        private Market marketId;

        private CoinBuilder() {
        }

        public static CoinBuilder aCoin() {
            return new CoinBuilder();
        }

        public CoinBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CoinBuilder setBaseAsset(String baseAsset) {
            this.baseAsset = baseAsset;
            return this;
        }

        public CoinBuilder setQuoteAsset(String quoteAsset) {
            this.quoteAsset = quoteAsset;
            return this;
        }

        public CoinBuilder setLastPrice(String lastPrice) {
            this.lastPrice = lastPrice;
            return this;
        }

        public CoinBuilder setVolumn24h(String volumn24h) {
            this.volumn24h = volumn24h;
            return this;
        }

        public CoinBuilder setMarketId(Market marketId) {
            this.marketId = marketId;
            return this;
        }

        public Coin build() {
            Coin coin = new Coin();
            coin.setName(name);
            coin.setBaseAsset(baseAsset);
            coin.setQuoteAsset(quoteAsset);
            coin.setLastPrice(lastPrice);
            coin.setVolumn24h(volumn24h);
            coin.setMarketId(marketId);
            return coin;
        }
    }
}
