package com.dashboards.heating.persistence;

import com.dashboards.heating.domain.Device;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository  extends ReactiveMongoRepository<Device, String> {
}
