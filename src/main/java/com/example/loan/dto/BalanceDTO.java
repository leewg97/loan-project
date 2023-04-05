package com.example.loan.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceDTO implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request {
        private Long applicationId;

        private BigDecimal entryAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {
        private Long balanceId;

        private Long applicationId;

        private BigDecimal balance;
    }
}
