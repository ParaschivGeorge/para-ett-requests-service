package com.paraett.requestsservice.controller;

import com.paraett.requestsservice.model.entities.Request;
import com.paraett.requestsservice.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("")
    public ResponseEntity<List<Request>> getAllRequests(@RequestParam(required = false) Long companyId, @RequestParam(required = false) Long managerId,
                                                     @RequestParam(required = false) Long userId, @RequestParam(required = false) Boolean approved,
                                                     @RequestParam(required = false) Date minDate, @RequestParam(required = false) Date maxDate) {
        List<Request> requests = requestService.getAllRequests(companyId, managerId, userId, approved, minDate, maxDate);

        return ResponseEntity.ok(requests);
    }

    @PostMapping("")
    public ResponseEntity<Object> createCompany(@RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdRequest.getId())
                .toUri();

        return ResponseEntity.created(location).body(request);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteRequests(@RequestParam(required = false) Long companyId, @RequestParam(required = false) Long managerId, @RequestParam(required = false) Long userId) {
        requestService.deleteRequests(companyId, managerId, userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequest(@PathVariable Long id) {
        Request request = requestService.getRequest(id);

        return ResponseEntity.ok(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request request) {
        Request updatedRequest = requestService.updateRequest(id, request);

        return ResponseEntity.accepted().body(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);

        return ResponseEntity.noContent().build();
    }
}
