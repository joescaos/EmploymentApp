package com.example.employmentApp.service.impl;

import com.example.employmentApp.model.Request;
import com.example.employmentApp.repositories.RequestsRepository;
import com.example.employmentApp.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements IRequestService {

    @Autowired
    RequestsRepository requestsRepository;

    @Override
    public void saveRequest(Request request) {
        requestsRepository.save(request);
    }
}
