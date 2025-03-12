package com.devsu.bankaccount.infrastructure.input.controller;

import com.devsu.bankaccount.application.dto.AccountStatementReport;
import com.devsu.bankaccount.infrastructure.input.dto.MovementRequestDTO;
import com.devsu.bankaccount.infrastructure.input.dto.MovementResponseDTO;
import com.devsu.bankaccount.infrastructure.input.dto.ReportResponseDTO;
import com.devsu.bankaccount.infrastructure.input.handler.MovementHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/movimientos")
public class MovementController {

    private final MovementHandler movementHandler;

    public MovementController(MovementHandler movementHandler) {
        this.movementHandler = movementHandler;
    }

    @GetMapping
    public ResponseEntity<List<MovementResponseDTO>> findAll() {
        return new ResponseEntity<>(movementHandler.getMovements(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovementResponseDTO> create(@RequestBody @Valid MovementRequestDTO movementRequestDTO) {

        MovementResponseDTO movementResponseDTO = movementHandler.createMovement(movementRequestDTO);
        return new ResponseEntity<>(movementResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MovementResponseDTO> update(@RequestBody @Valid MovementRequestDTO movementRequestDTO) {
        MovementResponseDTO movementResponseDTO = movementHandler.updateMovement(movementRequestDTO);
        return new ResponseEntity<>(movementResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/reportes")
    public ResponseEntity<ReportResponseDTO> generateReport(@RequestParam("fecha") String dateRange,
                                                            @RequestParam("cliente") Integer customerId) {
        // Split the date range to extract the start and end dates
        String[] dates = dateRange.split(",");

        // Convert the date strings into LocalDateTime with time 00:00:00 for start and 23:59:59 for end
        LocalDateTime startDate = LocalDate.parse(dates[0]).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(dates[1]).atTime(23, 59, 59, 999999999);

        // Generate the report using the LocalDateTime range
        ReportResponseDTO responseDTO = movementHandler.generateReport(customerId, startDate, endDate);

        // Return the report in the response
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
