package com.example.loan.service;

import com.example.loan.dto.EntryDTO.Request;
import com.example.loan.dto.EntryDTO.Response;

public interface EntryService {

    Response create(Long applicationId, Request request);

    Response get(Long applicationId);

}
