package com.meraki.packet.tracking.repository;

import com.meraki.packet.tracking.pojo.Packet;
import com.meraki.packet.tracking.pojo.PacketPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacketRepository extends JpaRepository<Packet,Long> {

    Packet findByPacketPK(PacketPK packetPK);
}