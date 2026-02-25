package com.Springboot.demo.Model;

import jakarta.persistence.*;

@Entity
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // CSE
    private Integer intake;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getIntake() { return intake; }
    public void setIntake(Integer intake) { this.intake = intake; }
}
