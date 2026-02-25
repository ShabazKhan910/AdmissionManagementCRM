package com.Springboot.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Springboot.demo.Model.Applicant;
import com.Springboot.demo.Repository.ApplicantRepository;
import com.Springboot.demo.Service.AdmissionService;

@Controller
public class AdmissionController {

    private final ApplicantRepository applicantRepo;
    private final AdmissionService admissionService;

    public AdmissionController(ApplicantRepository applicantRepo,
                               AdmissionService admissionService) {
        this.applicantRepo = applicantRepo;
        this.admissionService = admissionService;
    }

    // ✅ Home → Dashboard
    @GetMapping("/")
    public String home(Model model) {

        List<Applicant> applicants = applicantRepo.findAll();
        model.addAttribute("totalApplicants", applicants.size());
        model.addAttribute("applicants", applicants);

        return "dashboard";
    }

    // ✅ Open Applicant Form
    @GetMapping("/applicant-form")
    public String applicantForm(Model model) {
        model.addAttribute("applicant", new Applicant());
        return "applicant-form";
    }

    @PostMapping("/saveApplicant")
    @ResponseBody
    public Applicant saveApplicant(@RequestBody Applicant applicant) {
        return applicantRepo.save(applicant);
    }

    // ✅ Allocate Seat Page
    @GetMapping("/allocate-seat")
    public String allocateSeatPage() {
        return "allocate-seat";
    }

    // ✅ Allocate Seat Action
    @PostMapping("/allocateSeat")
    @ResponseBody
    public String allocateSeat(@RequestParam Long applicantId,
                               @RequestParam Long programId,
                               @RequestParam String quotaType) {

        return admissionService.allocateSeat(applicantId, programId, quotaType);
    }

    // ✅ Confirm Admission Page
    @GetMapping("/confirm-admission")
    public String confirmPage() {
        return "confirm-admission";
    }

    // ✅ Confirm Admission Action
    @PostMapping("/confirmAdmission")
    @ResponseBody
    public String confirmAdmission(@RequestParam Long applicantId) {
        return admissionService.confirmAdmission(applicantId);
    }
}