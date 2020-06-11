package com.dashboards.heating.service;

import com.dashboards.heating.domain.Device;
import com.dashboards.heating.persistence.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository repository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Device> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Device> create(@Valid Device device) {
        return repository.save(device);
    }

    @Override
    public Mono<Device> update(String id, @Valid Device device) {
        return repository.findById(id).map(current -> this.transform(device, current))
                .flatMap(repository::save);
    }

    @Override
    public Mono<Void> delete(@Valid String id) {
        return repository.deleteById(id);
    }

    @Override
    public Flux<Device> getAllDevices() {
        return repository.findAll();
    }

    private Device transform(Device newDevice, final Device currentDevice) {
        currentDevice.setCode(newDevice.getCode());
        currentDevice.setName(newDevice.getName());
        currentDevice.setDescription(newDevice.getDescription());
        return currentDevice;
    }


}
