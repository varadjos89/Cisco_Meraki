package com.meraki.packet.tracking.controller;

import com.meraki.packet.tracking.pojo.Packet;
import com.meraki.packet.tracking.service.PacketService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PacketController {

    @Autowired
    private PacketService packetService;

    /**
     * <api-description>
     * Get method to retrieve statistics for all packets
     * </api-description>
     *
     * <api-endpoint>
     * GET /packets/getstats
     * </api-endpoint>
     *
     * Response:
     * <api-response>
     * {
     *   "results": [
     *     {
     *         "device_id": long,
     *         "timestamp_start": long,
     *         "min": double,
     *         "max": double,
     *         "avg": double
     *     }
     *   ]
     * </api-response>
     */
    @GetMapping(path="/packets/getstats")
    public ResponseEntity<Optional> getAllPackets() {
        String message = null;
        try {
            List<Packet> packets = packetService.getAllPackets();
            return new ResponseEntity<>(Optional.of(packets), HttpStatus.OK);
        } catch (Exception e) {
            message = "{\"error\": \"Unable to fetch the data\"}";
            return new ResponseEntity<>(Optional.of(message), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <api-description>
     * Post method to send either a single packet or a list of packets
     * </api-description>
     *
     * <api-endpoint>
     * POST /packets/bulk/stats
     * </api-endpoint>
     *
     * Payload:
     * <api-payload>
     * {
     *   "device_id": string,
     *   "value": double,
     *   "timestamp": long
     * }
     * </api-payload>
     *
     * Response:
     * <api-response>
     * [
     *   {
     *     "success": string
     *     "statusCode": int
     *   }
     * ]
     * </api-response>
     */
    @PostMapping(path="/packets/bulk/stats")
    public ResponseEntity<Optional> calculateMinMaxAvg(@RequestBody List<Packet> packets) {
        String message = null;
        try {
            long startTimestamp = 0;
            long currentTimestamp = 0;
            for(Packet packet : packets) {
                currentTimestamp = packet.getTimestamp();
                /* Calculate startTimeStamp for every packet by subtracting currentTimestamp%60 from every timestamp */
                startTimestamp = currentTimestamp - (currentTimestamp % 60);
                Packet existingPacket = packetService.getPacket(startTimestamp, packet.getPacketPK().getDevice_id());
                if(existingPacket == null) {
                    /* Inserting packet stats for the first time in a given start TimeStamp for a device */
                    packet.setMin(packet.getValue());
                    packet.setMax(packet.getValue());
                    packet.setAvg(packet.getValue());
                    packet.getPacketPK().setTimestamp_start(startTimestamp);
                    packet.setCount(1);
                    packet.setSum(packet.getValue());
                    packetService.savePacket(packet);
                } else {
                    /* Retrieve existing packet and store it back by updating its Min, Max, Avg, Sum, Count */
                    existingPacket.setMax(Math.max(existingPacket.getMax(), packet.getValue()));
                    existingPacket.setMin(Math.min(existingPacket.getMin(), packet.getValue()));
                    existingPacket.setSum(existingPacket.getSum() + packet.getValue());
                    existingPacket.setCount(existingPacket.getCount() + 1);
                    existingPacket.setAvg(existingPacket.getSum() / existingPacket.getCount());
                    packetService.savePacket(existingPacket);
                }
            }
            message = "{\"Success\": \"Data successfully sent\"}";
            return new ResponseEntity<>(Optional.of(message), HttpStatus.OK);
        } catch (Exception e) {
            message = "{\"error\": \"Unable to send the data\"}";
            return new ResponseEntity<>(Optional.of(message), HttpStatus.BAD_REQUEST);
        }
    }

}
