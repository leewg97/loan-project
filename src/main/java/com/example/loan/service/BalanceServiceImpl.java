package com.example.loan.service;

import com.example.loan.domain.Balance;
import com.example.loan.dto.BalanceDTO.Request;
import com.example.loan.dto.BalanceDTO.Response;
import com.example.loan.exception.BaseException;
import com.example.loan.exception.ResultType;
import com.example.loan.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    private final ModelMapper modelMapper;

    @Override
    public Response create(Long applicationId, Request request) {
        if (balanceRepository.findByApplicationId(applicationId).isPresent()) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Balance balance = modelMapper.map(request, Balance.class);

        BigDecimal entryAmount = request.getEntryAmount();
        balance.setApplicationId(applicationId);
        balance.setBalance(entryAmount);

        Balance saved = balanceRepository.save(balance);

        return modelMapper.map(saved, Response.class);
    }
}

