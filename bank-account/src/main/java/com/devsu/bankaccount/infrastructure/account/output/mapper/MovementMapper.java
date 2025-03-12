package com.devsu.bankaccount.infrastructure.account.output.mapper;

import com.devsu.bankaccount.domain.model.Movement;
import com.devsu.bankaccount.infrastructure.account.output.entity.AccountEntity;
import com.devsu.bankaccount.infrastructure.account.output.entity.MovementEntity;

public class MovementMapper {
    public static Movement entityToMovement(MovementEntity movementEntity) {
        if (movementEntity == null) {
            return null;
        }

        Movement movement = new Movement();
        movement.setMovementId(movementEntity.getMovementId());
        movement.setDate(movementEntity.getDate());
        movement.setMovementType(movementEntity.getMovementType());
        movement.setValue(movementEntity.getValue());
        movement.setBalance(movementEntity.getBalance());
        movement.setAccountId(movementEntity.getAccount().getAccountId());
        return movement;
    }

    public static MovementEntity movementToEntity(Movement movement) {
        if (movement == null) {
            return null;
        }

        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setMovementId(movement.getMovementId());
        movementEntity.setDate(movement.getDate());
        movementEntity.setMovementType(movement.getMovementType());
        movementEntity.setValue(movement.getValue());
        movementEntity.setBalance(movement.getBalance());
        movementEntity.setAccount(new AccountEntity(movement.getAccountId()));

        return movementEntity;
    }
}
