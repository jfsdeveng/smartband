package com.juandev.SmartbandIME.smartband.controller;

import com.juandev.SmartbandIME.smartband.dto.HeartBeatDataDTO;
import com.juandev.SmartbandIME.smartband.dto.ResponseHeartBeatDTO;
import com.juandev.SmartbandIME.smartband.model.HeartBeatData;
import com.juandev.SmartbandIME.smartband.service.HeartBeatDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class HeartBeatDataController {

    @Autowired
    private HeartBeatDataService heartBeatDataService;


    @PostMapping("/heart-beat")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HeartBeatData criarProduto(@RequestBody HeartBeatDataDTO heartBeatDataDTO) {
        try{

            HeartBeatData heartBeatData = heartBeatDataService.saveHeartBeatData(heartBeatDataDTO);

            log.info(String.format("Heart Beat [%s]  from patientId [%s]",
                    heartBeatDataDTO.getHeartbeat(), heartBeatData.getPatientId()));

            return heartBeatData;

        }catch (Exception e){
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PostMapping("/heart-beat-influxdb")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity saveHeartRate(@RequestBody HeartBeatDataDTO heartBeatDataDTO) {
        try{

            String result = heartBeatDataService.saveHeartRateAtInfluxDB(heartBeatDataDTO);

            log.info(String.format("Heart Beat [%s]  from patientId [%s]",
                    heartBeatDataDTO.getHeartbeat(), heartBeatDataDTO.getPatientId()));

            return ResponseEntity.ok(ResponseHeartBeatDTO.builder().message("Created at data base").build());

        }catch (Exception e){
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return null;
    }
}
