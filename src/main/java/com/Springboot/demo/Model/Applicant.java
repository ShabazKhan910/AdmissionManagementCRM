package com.Springboot.demo.Model;

import jakarta.persistence.*;

@Entity
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private Double marks;

    private String category;
    private String entryType;
    private String quotaType;

    private String documentStatus = "Pending";
    private String feeStatus = "Pending";

    private Boolean seatAllocated = false;
    private String admissionNumber;

    @ManyToOne
    private Program program;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getEntryType() { return entryType; }
    public void setEntryType(String entryType) { this.entryType = entryType; }

    public String getQuotaType() { return quotaType; }
    public void setQuotaType(String quotaType) { this.quotaType = quotaType; }

    public String getDocumentStatus() { return documentStatus; }
    public void setDocumentStatus(String documentStatus) { this.documentStatus = documentStatus; }

    public String getFeeStatus() { return feeStatus; }
    public void setFeeStatus(String feeStatus) { this.feeStatus = feeStatus; }

    public Boolean getSeatAllocated() { return seatAllocated; }
    public void setSeatAllocated(Boolean seatAllocated) { this.seatAllocated = seatAllocated; }

    public String getAdmissionNumber() { return admissionNumber; }
    public void setAdmissionNumber(String admissionNumber) { this.admissionNumber = admissionNumber; }

    public Program getProgram() { return program; }
    public void setProgram(Program program) { this.program = program; }
}