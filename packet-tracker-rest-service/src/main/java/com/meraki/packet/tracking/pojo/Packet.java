package com.meraki.packet.tracking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Packet {

    @EmbeddedId
    private PacketPK packetPK;

    @JsonProperty(required = true)
    @Transient
    private Double value;

    @JsonProperty(required = true)
    @Transient
    private Long timestamp;

    @JsonIgnoreProperties
    @Column(nullable = false)
    private Double min;

    @JsonIgnoreProperties
    @Column(nullable = false)
    private Double max;

    @JsonIgnoreProperties
    @Column(nullable = false)
    private Double avg;

    @JsonIgnoreProperties
    @Column(nullable = false)
    private Double sum;

    @JsonIgnoreProperties
    @Column(nullable = false)
    private Integer count;

    public Packet() {
    }

    public PacketPK getPacketPK() {
        return packetPK;
    }

    public void setPacketPK(PacketPK packetPK) {
        this.packetPK = packetPK;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
