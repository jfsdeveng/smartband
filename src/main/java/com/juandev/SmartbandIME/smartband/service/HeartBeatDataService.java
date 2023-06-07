package com.juandev.SmartbandIME.smartband.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.juandev.SmartbandIME.InfluxDBConnectionClass;
import com.juandev.SmartbandIME.smartband.dto.HeartBeatDataDTO;
import com.juandev.SmartbandIME.smartband.model.HeartBeatData;
import com.juandev.SmartbandIME.smartband.repository.HeartBeatDataRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;



@Service
public class HeartBeatDataService {

    @Autowired
    private HeartBeatDataRepository heartBeatDataRepository;

    @Autowired
    private InfluxDBConnectionClass influxDBConnectionClass;

    private final String token = "-732KK7TMLCKOtgL-duA1r5_am61kXXD4dORrmoji6dgm9ASdHRvplycu2qkKb__S5hhi5vjV39pZzUkx0qLfQ==";
    private final String bucket = "smartband";
    private final String org = "IME";
    private final String url = "http://localhost:8086";

    public HeartBeatData saveHeartBeatData(HeartBeatDataDTO heartBeatDataDTO) {


        long timestamp = Long.parseLong(heartBeatDataDTO.getCreatedAtSensor());
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime createdAtDTO = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        HeartBeatData heartBeatData = HeartBeatData.builder()
                .heartbeat(heartBeatDataDTO.getHeartbeat())
                .patientId(heartBeatDataDTO.getPatientId())
                .patientName(heartBeatDataDTO.getPatientName())
                .smartBand(heartBeatDataDTO.getSmartBand())
                .createAtSensor(createdAtDTO)
                .build();

        return heartBeatDataRepository.save(heartBeatData);
    }

    public String saveHeartRateAtInfluxDB(HeartBeatDataDTO heartBeatDataDTO) throws Exception {

        try {
            InfluxDBClient influxDBClient = influxDBConnectionClass.buildConnection(url, token, bucket, org);

            WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

            Point point = Point.measurement("sensor").addTag("smartband", "Xiaomi 9")
                    .addField("location", "Centro - Rio de Janeiro")
                    .addField("patient_id", heartBeatDataDTO.getPatientId())
                    .addField("heart_rate", heartBeatDataDTO.getHeartbeat())
                    .time(Instant.now(), WritePrecision.MS);

            writeApi.writePoint(point);


            return "ok";

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
