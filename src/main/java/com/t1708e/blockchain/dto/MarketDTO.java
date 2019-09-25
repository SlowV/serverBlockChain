package com.t1708e.blockchain.dto;

import com.t1708e.blockchain.entity.Coin;
import com.t1708e.blockchain.entity.Market;
import com.t1708e.blockchain.util.DateTimeUtil;
import com.t1708e.blockchain.util.ObjectUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

public class MarketDTO {
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String status;

    public MarketDTO() {
    }

    public MarketDTO(Market market) {
        ObjectUtil.cloneObject(this, market);
        this.status = market.getStatus() == 1 ? "Active" : "Deactive";
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
