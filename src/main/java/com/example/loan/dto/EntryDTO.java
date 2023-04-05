package com.example.loan.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

public class EntryDTO implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request {

        private BigDecimal entryAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long applicationId;

        private BigDecimal entryAmount;
    }
}
