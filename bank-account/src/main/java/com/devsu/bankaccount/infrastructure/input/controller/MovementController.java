package com.devsu.bankaccount.infrastructure.input.controller;

import com.devsu.bankaccount.infrastructure.input.dto.MovementRequestDTO;
import com.devsu.bankaccount.infrastructure.input.dto.MovementResponseDTO;
import com.devsu.bankaccount.infrastructure.input.handler.MovementHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
