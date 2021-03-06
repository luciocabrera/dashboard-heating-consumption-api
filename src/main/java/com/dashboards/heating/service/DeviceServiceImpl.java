package com.dashboards.heating.service;

import com.dashboards.heating.domain.Device;
import com.dashboards.heating.persistence.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<Device>> findById(String id) {
        return repository.findById(id).map(existingDevice -> ResponseEntity.ok(existingDevice))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Device> create(@Valid Device device) {
        return repository.save(device);
    }

    @Override
    public Mono<ResponseEntity<Device>> update(String id, @Valid Device device) {
        return repository.findById(id)
                .flatMap(existingDevice -> {
                    existingDevice.setCode(device.getCode());
                    existingDevice.setName(device.getName());
                    existingDevice.setDescription(device.getDescription());
                    return repository.save(existingDevice);
                })
                .map(updatedDevice -> new ResponseEntity<>(updatedDevice, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Void>> delete(@Valid String id) {
        return repository.findById(id)
                .flatMap(existingDevice ->
                        repository.delete(existingDevice)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Flux<Device> getAllDevices() {
        return repository.findAll();
    }


}
