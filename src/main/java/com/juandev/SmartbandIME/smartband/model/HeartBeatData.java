package com.juandev.SmartbandIME.smartband.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "heart_beat")
@Data
@Builder
public class HeartBeatData {

    @Id
    private String id;

    private String patientId;
    private String patientName;
    private String smartBand;
    private Integer heartbeat;

    private LocalDateTime createAtSensor;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
