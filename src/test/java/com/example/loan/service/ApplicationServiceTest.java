package com.example.loan.service;

import com.example.loan.domain.AcceptTerms;
import com.example.loan.domain.Application;
import com.example.loan.domain.Terms;
import com.example.loan.dto.ApplicationDTO;
import com.example.loan.dto.ApplicationDTO.Request;
import com.example.loan.dto.ApplicationDTO.Response;
import com.example.loan.exception.BaseException;
import com.example.loan.repository.AcceptTermsRepository;
import com.example.loan.repository.ApplicationRepository;
import com.example.loan.repository.TermsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private TermsRepository termsRepository;

    @Mock
    private AcceptTermsRepository acceptTermsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewApplyEntity_When_RequestApply() {
        Application entity = Application.builder()
                .name("Kim")
                .cellPhone("010-1111-2222")
                .email("mail@mail.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .name("Kim")
                .cellPhone("010-1111-2222")
                .email("mail@mail.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);

        Response actual = applicationService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void Should_ReturnResponseOfExistApplicationEntity_When_RequestExistApplicationId() {
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.get(1L);

        assertThat(actual.getApplicationId()).isSameAs(findId);
    }

    @Test
    void Should_ReturnUpdatedResponseOfExistApplicationEntity_When_RequestUpdateExistApplicationInfo() {
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .name("Member Kim")
                .build();

        Request request = Request.builder()
                .name("Member Lee")
                .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.update(findId, request);

        assertThat(actual.getApplicationId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }

    @Test
    void Should_DeletedApplicationEntity_When_RequestDeleteExistApplicationInfo() {
        Long targetId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

        applicationService.delete(targetId);

        assertThat(entity.getIsDeleted()).isSameAs(true);
    }

    @Test
    void Should_AddAcceptTerms_When_RequestAcceptTermsOfApplication() {
        Terms entityA = Terms.builder()
                .termsId(1L)
                .name("대출 이용 약관 1")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdddads")
                .build();

        Terms entityB = Terms.builder()
                .termsId(2L)
                .name("대출 이용 약관 2")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdweqwq")
                .build();

        List<Long> acceptTerms = Arrays.asList(1L, 2L);

        ApplicationDTO.AcceptTermsDTO request = ApplicationDTO.AcceptTermsDTO.builder()
                .acceptTermsIds(acceptTerms)
                .build();

        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenReturn(
                Optional.ofNullable(Application.builder().build()));
        when(termsRepository.findAll(Sort.by(Direction.ASC, "termsId"))).thenReturn(Arrays.asList(entityA, entityB));
        when(acceptTermsRepository.save(ArgumentMatchers.any(AcceptTerms.class))).thenReturn(AcceptTerms.builder().build());


        Boolean actual = applicationService.acceptTerms(findId, request);
        assertThat(actual).isTrue();
    }

    @Test
    void Should_ThrowException_When_RequestNotAllAcceptTermsOfApplication() {
        Terms entityA = Terms.builder()
                .termsId(1L)
                .name("대출 이용 약관 1")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdddads")
                .build();

        Terms entityB = Terms.builder()
                .termsId(2L)
                .name("대출 이용 약관 2")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdweqwq")
                .build();

        List<Long> acceptTerms = Arrays.asList(1L);

        ApplicationDTO.AcceptTermsDTO request = ApplicationDTO.AcceptTermsDTO.builder()
                .acceptTermsIds(acceptTerms)
                .build();

        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenReturn(
                Optional.ofNullable(Application.builder().build()));
        when(termsRepository.findAll(Sort.by(Direction.ASC, "termsId"))).thenReturn(Arrays.asList(entityA, entityB));

        Assertions.assertThrows(BaseException.class, () -> applicationService.acceptTerms(1L, request));
    }

    @Test
    void Should_ThrowException_When_RequestNotExistAcceptTermsOfApplication() {
        Terms entityA = Terms.builder()
                .termsId(1L)
                .name("대출 이용 약관 1")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdddads")
                .build();

        Terms entityB = Terms.builder()
                .termsId(2L)
                .name("대출 이용 약관 2")
                .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsdweqwq")
                .build();

        List<Long> acceptTerms = Arrays.asList(1L, 3L);

        ApplicationDTO.AcceptTermsDTO request = ApplicationDTO.AcceptTermsDTO.builder()
                .acceptTermsIds(acceptTerms)
                .build();

        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenReturn(
                Optional.ofNullable(Application.builder().build()));
        when(termsRepository.findAll(Sort.by(Direction.ASC, "termsId"))).thenReturn(Arrays.asList(entityA, entityB));

        Assertions.assertThrows(BaseException.class, () -> applicationService.acceptTerms(1L, request));
    }

}