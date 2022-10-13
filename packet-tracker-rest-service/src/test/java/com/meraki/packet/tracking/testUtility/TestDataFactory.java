package com.meraki.packet.tracking.testUtility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meraki.packet.tracking.pojo.Packet;
import com.meraki.packet.tracking.pojo.PacketPK;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static String createPacketList() throws JsonProcessingException {
        Packet p1 = new Packet();
        PacketPK ppk1 = new PacketPK();
        ppk1.setDevice_id(1L);
        p1.setPacketPK(ppk1);
        p1.setValue(1.0);
        p1.setTimestamp(1611741600L);

        Packet p2 = new Packet();
        PacketPK ppk2 = new PacketPK();
        ppk2.setDevice_id(1L);
        p2.setPacketPK(ppk2);
        p2.setValue(2.0);
        p2.setTimestamp(1611741601L);

        Packet p3 = new Packet();
        PacketPK ppk3 = new PacketPK();
        ppk3.setDevice_id(2L);
        p3.setPacketPK(ppk3);
        p3.setValue(1.0);
        p3.setTimestamp(1611741602L);

        Packet p4 = new Packet();
        PacketPK ppk4 = new PacketPK();
        ppk4.setDevice_id(1L);
        p4.setPacketPK(ppk4);
        p4.setValue(6.0);
        p4.setTimestamp(1611741660L);

        Packet p5 = new Packet();
        PacketPK ppk5 = new PacketPK();
        ppk5.setDevice_id(2L);
        p5.setPacketPK(ppk5);
        p5.setValue(3.0);
        p5.setTimestamp(1611741659L);

        Packet p6 = new Packet();
        PacketPK ppk6 = new PacketPK();
        ppk6.setDevice_id(1L);
        p6.setPacketPK(ppk6);
        p6.setValue(9.0);
        p6.setTimestamp(1611741930L);

        List<Packet> packetList= new ArrayList<>();
        packetList.add(p1);
        packetList.add(p2);
        packetList.add(p3);
        packetList.add(p4);
        packetList.add(p5);
        packetList.add(p6);

        ObjectMapper objectMapper =  new ObjectMapper();
        return objectMapper.writeValueAsString(packetList);
    }

    public static String createInvalidPacket() throws JsonProcessingException {
        Packet p1 = new Packet();

        PacketPK ppk1 = new PacketPK();
        ppk1.setDevice_id(1L);
        p1.setPacketPK(ppk1);

        p1.setTimestamp(1611741600L);

        ObjectMapper objectMapper =  new ObjectMapper();
        return objectMapper.writeValueAsString(p1);
    }
}
