package com.devsu.bankaccount.infrastructure.output.adapter;

import com.devsu.bankaccount.application.port.ouput.IMovementrepository;
import com.devsu.bankaccount.domain.model.Movement;
import com.devsu.bankaccount.infrastructure.output.entity.MovementEntity;
import com.devsu.bankaccount.infrastructure.output.mapper.MovementMapper;
import com.devsu.bankaccount.infrastructure.output.repository.IOutputMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ImplMovementRepository implements IMovementrepository {

    private final IOutputMovementRepository movementRepository;

    public ImplMovementRepository(IOutputMovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public List<Movement> findAll() {
        List<MovementEntity> movementEntities = movementRepository.findAll();
        return movementEntities.stream()
                .map(MovementMapper::entityToMovement)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movement> findById(Integer id) {
        Optional<MovementEntity> movementEntity = movementRepository.findById(id);
        return movementEntity.map(MovementMapper::entityToMovement);
    }

    @Override
    public Optional<Movement> findByAccountId(Integer accountId) {
        List<MovementEntity> movementEntities = movementRepository.findByAccount_AccountId(accountId);
        if (movementEntities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(MovementMapper.entityToMovement(movementEntities.get(0)));
    }

    @Override
    public Movement save(Movement movement) {
        MovementEntity movementEntity = MovementMapper.movementToEntity(movement);
        MovementEntity savedEntity = movementRepository.save(movementEntity);
        return MovementMapper.entityToMovement(savedEntity);
    }

    @Override
    public Movement update(Movement movement) {
        Optional<MovementEntity> existingEntity = movementRepository.findById(movement.getMovementId());

        if (existingEntity.isPresent()) {
            MovementEntity updatedEntity = existingEntity.get();
            updatedEntity.setDate(movement.getDate());
            updatedEntity.setMovementType(movement.getMovementType());
            updatedEntity.setValue(movement.getValue());
            updatedEntity.setBalance(movement.getBalance());
            movementRepository.save(updatedEntity);
            return MovementMapper.entityToMovement(updatedEntity);
        } else {
            throw new EntityNotFoundException("Movement not found");
        }
    }

    @Override
    public Movement delete(Movement movement) {
        Optional<MovementEntity> existingEntity = movementRepository.findById(movement.getMovementId());

        if (existingEntity.isPresent()) {
            movementRepository.delete(existingEntity.get());
            return movement;
        } else {
            throw new EntityNotFoundException("Movement not found");
        }
    }

    @Override
    public List<Movement> findByAccount_AccountIdAndDateBetween(Integer accountId, LocalDateTime startDate, LocalDateTime endDate) {
        List<MovementEntity> reportEntity = movementRepository.findByAccount_AccountIdAndDateBetween(accountId,startDate,endDate);

        return reportEntity.stream()
                .map(MovementMapper::entityToMovement)
                .collect(Collectors.toList());
    }

}
