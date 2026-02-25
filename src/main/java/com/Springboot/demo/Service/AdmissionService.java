package com.Springboot.demo.Service;

public interface AdmissionService {

    String allocateSeat(Long applicantId, Long programId, String quotaType);

    String confirmAdmission(Long applicantId);
}