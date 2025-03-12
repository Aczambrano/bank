package com.devsu.bankaccount.infrastructure.output.adapter;

import com.devsu.bankaccount.application.port.ouput.IMovementrepository;
import com.devsu.bankaccount.domain.model.Movement;
import com.devsu.bankaccount.infrastructure.output.entity.MovementEntity;
import com.devsu.bankaccount.infrastructure.output.mapper.MovementMapper;
import com.devsu.bankaccount.infrastructure.output.repository.IOutputMovementRepository;
import org.springframework.stereotype.Repository;

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
        List<MovementEntity> transactionEntities = movementRepository.findAll();
        return transactionEntities.stream()
                .map(MovementMapper::entityToMovement)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movement> findById(Integer id) {
        Optional<MovementEntity> transactionEntity = movementRepository.findById(id);
        return transactionEntity.map(MovementMapper::entityToMovement);
    }

    @Override
    public Optional<Movement> findByAccountId(Integer accountId) {
        List<MovementEntity> transactionEntities = movementRepository.findByAccount_AccountId(accountId);
        if (transactionEntities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(MovementMapper.entityToMovement(transactionEntities.get(0)));
    }

    @Override
    public Movement save(Movement transaction) {
        MovementEntity transactionEntity = MovementMapper.movementToEntity(transaction);
        MovementEntity savedEntity = movementRepository.save(transactionEntity);
        return MovementMapper.entityToMovement(savedEntity);
    }

    @Override
    public Movement update(Movement transaction) {
        Optional<MovementEntity> existingEntity = movementRepository.findById(transaction.getMovementId());

        if (existingEntity.isPresent()) {
            MovementEntity updatedEntity = existingEntity.get();
            updatedEntity.setDate(transaction.getDate());
            updatedEntity.setMovementType(transaction.getMovementType());
            updatedEntity.setValue(transaction.getValue());
            updatedEntity.setBalance(transaction.getBalance());
            movementRepository.save(updatedEntity);
            return MovementMapper.entityToMovement(updatedEntity);
        } else {
            throw new RuntimeException("Movement not found");
        }
    }

    @Override
    public Movement delete(Movement transaction) {
        Optional<MovementEntity> existingEntity = movementRepository.findById(transaction.getMovementId());

        if (existingEntity.isPresent()) {
            movementRepository.delete(existingEntity.get());
            return transaction;
        } else {
            throw new RuntimeException("Movement not found");
        }
    }

}
