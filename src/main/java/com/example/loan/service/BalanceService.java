package com.example.loan.service;

import com.example.loan.dto.BalanceDTO.CreateRequest;
import com.example.loan.dto.BalanceDTO.Response;
import com.example.loan.dto.BalanceDTO.UpdateRequest;

public interface BalanceService {

    Response create(Long applicationId, CreateRequest createRequest);

    Response update(Long applicationId, UpdateRequest updateRequest);
}
