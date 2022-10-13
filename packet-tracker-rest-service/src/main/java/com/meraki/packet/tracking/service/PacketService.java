package com.meraki.packet.tracking.service;

import com.meraki.packet.tracking.pojo.PacketPK;
import com.meraki.packet.tracking.repository.PacketRepository;
import com.meraki.packet.tracking.pojo.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacketService {

    @Autowired
    private PacketRepository packetRepository;

    public List<Packet> getAllPackets() {
        return packetRepository.findAll();
    }


    public Packet getPacket(long timestamp_start, long device_id) {
        return packetRepository.findByPacketPK(new PacketPK(timestamp_start, device_id));
    }

    public Packet savePacket(Packet packet){
          return packetRepository.save(packet);
    }
}
