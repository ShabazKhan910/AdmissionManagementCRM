package com.Springboot.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Springboot.demo.Model.Applicant;
import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    List<Applicant> findByFeeStatus(String status);
    List<Applicant> findByDocumentStatus(String status);
}