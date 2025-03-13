package com.devsu.bankaccount.application.mapper;

import com.devsu.bankaccount.application.dto.MovementReportDTO;
import com.devsu.bankaccount.domain.model.Movement;
import com.devsu.bankaccount.infrastructure.input.dto.MovementResponseDTO;

public class MovementReportDTOMapper {

    public static MovementReportDTO toMovementReportDTO(Movement movement) {
        return new MovementReportDTO(
                movement.getMovementId(),
                movement.getAccountId(),
                movement.getDate(),
                movement.getMovementType(),
                movement.getValue(),
                movement.getBalance()
        );
    }
}
