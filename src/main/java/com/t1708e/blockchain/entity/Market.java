package com.t1708e.blockchain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Market {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private long createdAt;
    private long updatedAt;
    private int status;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Coin> coin;

    public Market() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Coin> getCoin() {
        return coin;
    }

    public void setCoin(List<Coin> coin) {
        this.coin = coin;
    }

    public static final class MarketBuilder {
        private String name;
        private String description;

        private MarketBuilder() {
        }

        public static MarketBuilder aMarket() {
            return new MarketBuilder();
        }

        public MarketBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public MarketBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Market build() {
            Market market = new Market();
            market.setName(name);
            market.setDescription(description);
            return market;
        }
    }
}
