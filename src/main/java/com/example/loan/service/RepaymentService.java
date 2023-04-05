package com.example.loan.service;

import com.example.loan.dto.RepaymentDTO.ListResponse;
import com.example.loan.dto.RepaymentDTO.Request;
import com.example.loan.dto.RepaymentDTO.Response;
import com.example.loan.dto.RepaymentDTO.UpdateResponse;

import java.util.List;

public interface RepaymentService {

    Response create(Long applicationId, Request request);

    List<ListResponse> get(Long applicationId);

    UpdateResponse update(Long repaymentId, Request request);

    void delete(Long repaymentId);

}
