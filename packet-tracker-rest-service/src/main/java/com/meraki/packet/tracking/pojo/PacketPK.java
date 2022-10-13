package com.meraki.packet.tracking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PacketPK implements Serializable {

    @JsonIgnoreProperties
    private Long timestamp_start;

    @JsonProperty(required = true)
    private Long device_id;

    public PacketPK(Long timestamp_start, Long device_id) {
        this.timestamp_start = timestamp_start;
        this.device_id = device_id;
    }

    public PacketPK() {

    }

    public Long getTimestamp_start() {
        return timestamp_start;
    }

    public void setTimestamp_start(Long timestamp_start) {
        this.timestamp_start = timestamp_start;
    }

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }
}
