package com.example.loan.service;

import com.example.loan.dto.BalanceDTO.CreateRequest;
import com.example.loan.dto.BalanceDTO.RepaymentRequest;
import com.example.loan.dto.BalanceDTO.Response;
import com.example.loan.dto.BalanceDTO.UpdateRequest;

public interface BalanceService {

    Response create(Long applicationId, CreateRequest request);

    Response get(Long applicationId);

    Response update(Long applicationId, UpdateRequest request);

    Response repaymentUpdate(Long applicationId, RepaymentRequest request);

    void delete(Long applicationId);
}
