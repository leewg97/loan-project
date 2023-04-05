package com.example.loan.service;

import com.example.loan.domain.Counsel;
import com.example.loan.dto.CounselDTO.Request;
import com.example.loan.dto.CounselDTO.Response;
import com.example.loan.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
        Counsel entity = Counsel.builder()
                .name("Kim")
                .cellPhone("010-1111-2222")
                .email("mail@mail.com")
                .memo("I hope to get a loan")
                .zipCode("123456")
                .address("Gangnam-gu, Seoul")
                .addressDetail("Apartment No. 101, 1st floor No. 101")
                .build();

        Request request = Request.builder()
                .name("Kim")
                .cellPhone("010-1111-2222")
                .email("mail@mail.com")
                .memo("I hope to get a loan")
                .zipCode("123456")
                .address("Gangnam-gu, Seoul")
                .addressDetail("Apartment No. 101, 1st floor No. 101")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

}
