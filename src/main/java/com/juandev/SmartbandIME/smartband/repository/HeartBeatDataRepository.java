package com.juandev.SmartbandIME.smartband.repository;

import com.juandev.SmartbandIME.smartband.model.HeartBeatData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartBeatDataRepository extends MongoRepository<HeartBeatData, String> {
}
