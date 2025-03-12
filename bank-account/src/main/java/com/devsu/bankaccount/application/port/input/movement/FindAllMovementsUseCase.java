package com.devsu.bankaccount.application.port.input.movement;

import com.devsu.bankaccount.application.port.ouput.IMovementrepository;
import com.devsu.bankaccount.domain.model.Movement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllMovementsUseCase {

    private final IMovementrepository movementRepository;

    public FindAllMovementsUseCase(IMovementrepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public List<Movement> execute() {
        return movementRepository.findAll();
    }

}
