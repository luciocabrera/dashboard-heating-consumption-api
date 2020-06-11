package com.dashboards.heating.service;

import com.dashboards.heating.domain.Device;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/devices")
@Api(value = "Devices", description = "Operations pertaining to devices")
public interface DeviceService {

    @GetMapping()
    @ResponseBody
    @ApiOperation(value = "Get all the devices")
    Flux<Device> getAllDevices();

    @GetMapping(
            value = "/findById/{id}",
            produces = "application/json")
    @ApiOperation(value = "Get a device by its Id")
    Mono<Device> findById(@PathVariable final String id);

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new device")
    Mono<Device> create(@Valid @RequestBody final Device device);

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a device for a given id and valid body")
    Mono<Device> update(@PathVariable final String id, @Valid @RequestBody Device device);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a device for a given id")
    Mono<Void> delete(@PathVariable final String id);
}
