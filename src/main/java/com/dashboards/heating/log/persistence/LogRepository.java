package com.dashboards.heating.log.persistence;

import com.dashboards.heating.log.domain.Log;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends ReactiveMongoRepository<Log, String> {
}
