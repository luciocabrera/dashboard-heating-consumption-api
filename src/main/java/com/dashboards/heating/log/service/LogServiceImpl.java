package com.dashboards.heating.log.service;

import com.dashboards.heating.log.domain.Log;
import com.dashboards.heating.log.persistence.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class LogServiceImpl implements LogService {
    private final LogRepository repository;

    @Autowired
    public LogServiceImpl(LogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ResponseEntity<Log>> findById(String id) {
        return repository.findById(id).map(existingDevice -> ResponseEntity.ok(existingDevice))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Log> create(@Valid Log log) {
        return repository.save(log);
    }

    @Override
    public Mono<ResponseEntity<Log>> update(String id, @Valid Log log) {
        return repository.findById(id)
                .flatMap(existingLog -> {
                    existingLog.setTimestamp(log.getTimestamp());
                    existingLog.setReading(log.getReading());
                    existingLog.setComment(log.getComment());
                    return repository.save(existingLog);
                })
                .map(updatedDevice -> new ResponseEntity<>(updatedDevice, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Void>> delete(@Valid String id) {
        return repository.findById(id)
                .flatMap(existingLog ->
                        repository.delete(existingLog)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Flux<Log> getAllLogs() {
        return repository.findAll();
    }
}
