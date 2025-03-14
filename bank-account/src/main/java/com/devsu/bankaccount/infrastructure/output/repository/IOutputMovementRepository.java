package com.devsu.bankaccount.infrastructure.output.repository;

import com.devsu.bankaccount.domain.model.Movement;
import com.devsu.bankaccount.infrastructure.output.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOutputMovementRepository extends JpaRepository<MovementEntity, Integer> {
    Optional<MovementEntity> findById(Integer id);
    List<MovementEntity> findByAccount_AccountId(Integer accountId);
    List<MovementEntity> findAll();
    List<MovementEntity> findByAccount_AccountIdAndDateBetween(Integer accountId, LocalDateTime startDate, LocalDateTime endDate);

}
