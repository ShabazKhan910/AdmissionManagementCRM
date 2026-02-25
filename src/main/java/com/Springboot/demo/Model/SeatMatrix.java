package com.Springboot.demo.Model;

import jakarta.persistence.*;

@Entity
public class SeatMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Program program;

    private String quotaType; // KCET / COMEDK / MANAGEMENT
    private Integer totalSeats;
    private Integer filledSeats = 0;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Program getProgram() { return program; }
    public void setProgram(Program program) { this.program = program; }

    public String getQuotaType() { return quotaType; }
    public void setQuotaType(String quotaType) { this.quotaType = quotaType; }

    public Integer getTotalSeats() { return totalSeats; }
    public void setTotalSeats(Integer totalSeats) { this.totalSeats = totalSeats; }

    public Integer getFilledSeats() { return filledSeats; }
    public void setFilledSeats(Integer filledSeats) { this.filledSeats = filledSeats; }
}