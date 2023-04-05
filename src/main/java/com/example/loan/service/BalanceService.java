package com.example.loan.service;

import com.example.loan.dto.BalanceDTO.Request;
import com.example.loan.dto.BalanceDTO.Response;

public interface BalanceService {

    Response create(Long applicationId, Request request);
}
