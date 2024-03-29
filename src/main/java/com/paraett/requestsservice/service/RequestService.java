package com.paraett.requestsservice.service;

import com.paraett.requestsservice.exception.NotFoundException;
import com.paraett.requestsservice.model.entities.Request;
import com.paraett.requestsservice.model.enums.RequestStatus;
import com.paraett.requestsservice.repository.RequestRepository;
import com.paraett.requestsservice.repository.RequestSpecifications;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getAllRequests(Long companyId, Long managerId, Long userId, RequestStatus status, Date minDate, Date maxDate) {
        return requestRepository.findAll(RequestSpecifications.findAllFiltered(companyId, managerId, userId, status, minDate, maxDate));
    }

    public Request getRequest(Long id) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            return optionalRequest.get();
        }
        throw new NotFoundException("id: " + id);
    }

    public Request createRequest(Request request) {
        request.setStatus(RequestStatus.PENDING);
        return requestRepository.save(request);
    }

    public Request updateRequest(Long id, Request request) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            request.setId(id);
            return requestRepository.save(request);
        }
        throw new NotFoundException("id: " + id);
    }

    public void deleteRequest(Long id) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            requestRepository.deleteById(id);
        } else {
            throw new NotFoundException("id: " + id);
        }
    }

    public void deleteRequests(Long companyId, Long managerId, Long userId) {
        if (companyId != null) {
            requestRepository.deleteAllByCompanyId(companyId);
        }
        if (managerId != null) {
            requestRepository.deleteAllByManagerId(managerId);
        }
        if (userId != null) {
            requestRepository.deleteAllByUserId(userId);
        }
    }
}
