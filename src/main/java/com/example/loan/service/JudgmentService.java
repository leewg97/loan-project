package com.example.loan.service;

import com.example.loan.dto.JudgmentDTO.Request;
import com.example.loan.dto.JudgmentDTO.Response;

public interface JudgmentService {

    Response create(Request request);

    Response get(Long judgmentId);

    Response getJudgmentOfApplication(Long applicationId);

    Response update(Long judgmentId, Request request);

}
