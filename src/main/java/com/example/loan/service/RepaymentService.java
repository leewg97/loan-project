package com.example.loan.service;

import com.example.loan.dto.RepaymentDTO.ListResponse;
import com.example.loan.dto.RepaymentDTO.Request;
import com.example.loan.dto.RepaymentDTO.Response;

import java.util.List;

public interface RepaymentService {

    Response create(Long applicationId, Request request);

    List<ListResponse> get(Long applicationId);

}
