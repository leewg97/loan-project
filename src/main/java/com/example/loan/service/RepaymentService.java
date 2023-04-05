package com.example.loan.service;

import com.example.loan.dto.RepaymentDTO.Request;
import com.example.loan.dto.RepaymentDTO.Response;

public interface RepaymentService {

    Response create(Long applicationId, Request request);

}
