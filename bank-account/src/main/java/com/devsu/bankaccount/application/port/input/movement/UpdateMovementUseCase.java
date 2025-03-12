package com.devsu.bankaccount.application.port.input.movement;

import com.devsu.bankaccount.application.port.ouput.IMovementrepository;
import com.devsu.bankaccount.domain.model.Movement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class UpdateMovementUseCase {

    private final IMovementrepository movementRepository;

    public UpdateMovementUseCase(IMovementrepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public Movement execute(Movement movement) {
        return movementRepository.findByAccountId(movement.getAccountId())
                .map(existingMovement -> {
                    existingMovement.setMovementType(movement.getMovementType());
                    existingMovement.setValue(movement.getValue());
                    existingMovement.setBalance(movement.getBalance());
                    existingMovement.setDate(LocalDateTime.now());
                    return movementRepository.update(existingMovement);
                })
                .orElseThrow(() -> new NoSuchElementException("The movement with the given account id does not exist."));
    }

}
