package com.devsu.bankaccount.infrastructure.input.handler;

import com.devsu.bankaccount.application.dto.AccountStatementReport;
import com.devsu.bankaccount.application.port.input.movement.CreateMovementUseCase;
import com.devsu.bankaccount.application.port.input.movement.DeleteMovementUseCase;
import com.devsu.bankaccount.application.port.input.movement.FindAllMovementsUseCase;
import com.devsu.bankaccount.application.port.input.movement.UpdateMovementUseCase;
import com.devsu.bankaccount.application.port.input.report.GenerateReportUseCase;
import com.devsu.bankaccount.domain.model.Movement;
import com.devsu.bankaccount.infrastructure.input.dto.MovementRequestDTO;
import com.devsu.bankaccount.infrastructure.input.dto.MovementResponseDTO;
import com.devsu.bankaccount.infrastructure.input.dto.ReportResponseDTO;
import com.devsu.bankaccount.infrastructure.input.mapper.MovementDTOMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovementHandler {

    private final CreateMovementUseCase createMovementUseCase;
    private final UpdateMovementUseCase updateMovementUseCase;
    private final DeleteMovementUseCase deleteMovementUseCase;
    private final FindAllMovementsUseCase findAllMovementsUseCase;
    private final GenerateReportUseCase generateReportUseCase;
    public MovementHandler(CreateMovementUseCase createMovementUseCase, UpdateMovementUseCase updateMovementUseCase, DeleteMovementUseCase deleteMovementUseCase, FindAllMovementsUseCase findAllMovementsUseCase, GenerateReportUseCase generateReportUseCase) {
        this.createMovementUseCase = createMovementUseCase;
        this.updateMovementUseCase = updateMovementUseCase;
        this.deleteMovementUseCase = deleteMovementUseCase;
        this.findAllMovementsUseCase = findAllMovementsUseCase;
        this.generateReportUseCase = generateReportUseCase;
    }


    public List<MovementResponseDTO> getMovements() {
        return findAllMovementsUseCase.execute().stream()
                .map(MovementDTOMapper::toMovementResponseDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public MovementResponseDTO createMovement(MovementRequestDTO movementRequestDTO) {
        Movement movement = MovementDTOMapper.toMovement(movementRequestDTO);
        return MovementDTOMapper.toMovementResponseDTO(createMovementUseCase.execute(movement));
    }

    public MovementResponseDTO updateMovement(MovementRequestDTO movementRequestDTO) {
        Movement movement = MovementDTOMapper.toMovement(movementRequestDTO);
        return MovementDTOMapper.toMovementResponseDTO(updateMovementUseCase.execute(movement));
    }

    public void deleteMovement(Integer movementId) {
        deleteMovementUseCase.execute(movementId);
    }

    public ReportResponseDTO generateReport(Integer customerId, LocalDateTime startDate, LocalDateTime endDate) {
        AccountStatementReport accountStatementReport = generateReportUseCase.execute(customerId, startDate, endDate);
        return MovementDTOMapper.toReportResponseDTO(accountStatementReport);
    }


}
