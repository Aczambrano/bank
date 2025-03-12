package com.devsu.bankaccount.infrastructure.input.mapper;

import com.devsu.bankaccount.domain.model.Movement;
import com.devsu.bankaccount.infrastructure.input.dto.MovementRequestDTO;
import com.devsu.bankaccount.infrastructure.input.dto.MovementResponseDTO;

public class MovementDTOMapper {

    public static Movement toMovement(MovementRequestDTO movementRequestDTO) {
        Movement movement = new Movement();
        movement.setMovementId(movementRequestDTO.getMovementId());
        movement.setAccountId(movementRequestDTO.getAccountId());
        movement.setMovementType(movementRequestDTO.getMovementType());
        movement.setValue(movementRequestDTO.getValue());
        movement.setBalance(movementRequestDTO.getBalance());
        return movement;
    }

    public static MovementResponseDTO toMovementResponseDTO(Movement movement) {
        return new MovementResponseDTO(
                movement.getMovementId(),
                movement.getAccountId(),
                movement.getMovementType(),
                movement.getValue(),
                movement.getBalance()
        );
    }
}
