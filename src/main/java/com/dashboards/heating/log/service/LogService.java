package com.dashboards.heating.log.service;

import com.dashboards.heating.log.domain.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/logs")
@Api(value = "Logs", description = "Operations pertaining to entry logs")
public interface LogService {
    @GetMapping()
    @ResponseBody
    @ApiOperation(value = "Get all the logs")
    Flux<Log> getAllLogs();

    @GetMapping(
            value = "/findById/{id}",
            produces = "application/json")
    @ApiOperation(value = "Get a entry log by its Id")
    Mono<ResponseEntity<Log>> findById(@PathVariable final String id);

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new entry log")
    Mono<Log> create(@Valid @RequestBody final Log log);

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a entry log for a given id and valid body")
    Mono<ResponseEntity<Log>> update(@PathVariable final String id, @Valid @RequestBody Log log);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a entry log for a given id")
    Mono<ResponseEntity<Void>> delete(@PathVariable final String id);

}
