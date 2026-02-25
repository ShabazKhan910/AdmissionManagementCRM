package com.Springboot.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Springboot.demo.Model.Applicant;
import com.Springboot.demo.Model.Program;
import com.Springboot.demo.Model.SeatMatrix;
import com.Springboot.demo.Repository.ApplicantRepository;
import com.Springboot.demo.Repository.SeatMatrixRepository;

@Service
@Transactional
public class AdmissionServiceImpl implements AdmissionService {

    private final ApplicantRepository applicantRepo;
    private final SeatMatrixRepository seatRepo;

    public AdmissionServiceImpl(ApplicantRepository applicantRepo,
                                SeatMatrixRepository seatRepo) {
        this.applicantRepo = applicantRepo;
        this.seatRepo = seatRepo;
    }

    // ✅ Seat Allocation Logic
    @Override
    public String allocateSeat(Long applicantId, Long programId, String quotaType) {

        Applicant applicant = applicantRepo.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        SeatMatrix seat = seatRepo
                .findByProgramIdAndQuotaType(programId, quotaType)
                .orElseThrow(() -> new RuntimeException("Quota not found"));

        // 🚨 RULE: No overbooking
        if (seat.getFilledSeats() >= seat.getTotalSeats()) {
            throw new RuntimeException("Quota Full — Cannot Allocate Seat");
        }

        // update counter
        seat.setFilledSeats(seat.getFilledSeats() + 1);

        applicant.setSeatAllocated(true);
        applicant.setQuotaType(quotaType);

        // attach program reference
        Program program = new Program();
        program.setId(programId);
        applicant.setProgram(program);

        applicantRepo.save(applicant);
        seatRepo.save(seat);

        return "Seat Allocated Successfully";
    }

    // ✅ Admission Confirmation Logic
    @Override
    public String confirmAdmission(Long applicantId) {

        Applicant applicant = applicantRepo.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        // 🚨 RULE: Fee must be paid
        if (!"Paid".equalsIgnoreCase(applicant.getFeeStatus())) {
            throw new RuntimeException("Admission blocked — Fee not paid");
        }

        // 🚨 RULE: immutable admission number
        if (applicant.getAdmissionNumber() != null) {
            return applicant.getAdmissionNumber();
        }

        long count = applicantRepo.count() + 1;

        String admissionNo = String.format(
                "INST/2026/UG/%s/%s/%04d",
                applicant.getProgram().getName(),
                applicant.getQuotaType(),
                count
        );

        applicant.setAdmissionNumber(admissionNo);
        applicantRepo.save(applicant);

        return admissionNo;
    }
}