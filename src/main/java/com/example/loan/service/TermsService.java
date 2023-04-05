package com.example.loan.service;

import com.example.loan.dto.TermsDTO.Request;
import com.example.loan.dto.TermsDTO.Response;

import java.util.List;

public interface TermsService {

    Response create(Request request);

    List<Response> getAll();

}
