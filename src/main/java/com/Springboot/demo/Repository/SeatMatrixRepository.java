package com.Springboot.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Springboot.demo.Model.SeatMatrix;
import java.util.Optional;

public interface SeatMatrixRepository extends JpaRepository<SeatMatrix, Long> {

    Optional<SeatMatrix> findByProgramIdAndQuotaType(Long programId, String quotaType);
}