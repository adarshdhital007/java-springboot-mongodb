package com.adarsh.app.Rest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRecordRepository extends MongoRepository<DataRecord, String> {
    // Custom queries can be added here if needed
}
