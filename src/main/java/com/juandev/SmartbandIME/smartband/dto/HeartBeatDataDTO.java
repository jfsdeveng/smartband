package com.juandev.SmartbandIME.smartband.dto;

import lombok.Data;

@Data
public class HeartBeatDataDTO {

    private String patientId;
    private String patientName;
    private String smartBand;
    private String createdAtSensor;
    private Integer heartbeat;
}
