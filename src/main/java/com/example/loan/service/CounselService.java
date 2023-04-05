package com.example.loan.service;

import com.example.loan.dto.CounselDTO.Request;
import com.example.loan.dto.CounselDTO.Response;

public interface CounselService {

    Response create(Request request);

}
