package com.meraki.packet.tracking.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meraki.packet.tracking.pojo.Packet;
import com.meraki.packet.tracking.testUtility.TestDataFactory;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PacketControllerTest {

    @LocalServerPort
    int serverPort;

    @Test
    public void postiveTest() throws JsonProcessingException, URISyntaxException, JSONException {
        var packetRequestString = TestDataFactory.createPacketList();

        var restTemplate = new RestTemplate();
        var baseUrl = "http://localhost:" + serverPort + "/packets/bulk/stats";
        var uri = new URI(baseUrl);
        var headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity(packetRequestString, headers);
        var result = restTemplate.postForEntity(uri, request, String.class);
        Assertions.assertEquals(result.getStatusCodeValue(), 200);

        var getBaseUrl = "http://localhost:" + serverPort + "/packets/getstats";
        var getURI = new URI(getBaseUrl);
        var getHeaders = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<Packet>> getResult = restTemplate.exchange(getURI, HttpMethod.GET, new HttpEntity<>(getHeaders), new ParameterizedTypeReference<List<Packet>>() {});
        Assertions.assertEquals(getResult.getStatusCodeValue(), 200);

        for(Packet packet : getResult.getBody()) {
            if(packet.getPacketPK().getTimestamp_start() == 1611741900L) {
                Assertions.assertEquals(packet.getPacketPK().getDevice_id(), 1L);
                Assertions.assertEquals(packet.getMax(), 9.0);
                Assertions.assertEquals(packet.getMin(), 9.0);
                Assertions.assertEquals(packet.getAvg(), 9.0);
            } else if(packet.getPacketPK().getTimestamp_start() == 1611741660L) {
                Assertions.assertEquals(packet.getPacketPK().getDevice_id(), 1L);
                Assertions.assertEquals(packet.getMax(), 6.0);
                Assertions.assertEquals(packet.getMin(), 6.0);
                Assertions.assertEquals(packet.getAvg(), 6.0);
            } else if(packet.getPacketPK().getDevice_id() == 1L){
                Assertions.assertEquals(packet.getMax(), 2.0);
                Assertions.assertEquals(packet.getMin(), 1.0);
                Assertions.assertEquals(packet.getAvg(), 1.5);
            } else {
                Assertions.assertEquals(packet.getMax(), 3.0);
                Assertions.assertEquals(packet.getMin(), 1.0);
                Assertions.assertEquals(packet.getAvg(), 2.0);
            }
        }
    }

    /* The test fails because we try to send a packet without a value attribute */
    @Test
    public void negativeTest() throws JsonProcessingException, URISyntaxException {
        var packetRequestString = TestDataFactory.createInvalidPacket();

        var restTemplate = new RestTemplate();
        var baseUrl = "http://localhost:" + serverPort + "/packets/bulk/stats";
        var uri = new URI(baseUrl);
        var headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity(packetRequestString, headers);

        HttpClientErrorException exceptionHandler = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.postForEntity(uri, request, String.class);
        });

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionHandler.getStatusCode());
    }

}
