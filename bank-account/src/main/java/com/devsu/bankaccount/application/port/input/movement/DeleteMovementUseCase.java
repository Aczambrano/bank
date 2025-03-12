package com.devsu.bankaccount.application.port.input.movement;

import com.devsu.bankaccount.application.port.ouput.IMovementrepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DeleteMovementUseCase {

    private final IMovementrepository movementRepository;

    public DeleteMovementUseCase(IMovementrepository movementRepository) {
        this.movementRepository = movementRepository;
    }


    public void execute(Integer id) {
        movementRepository.findById(id)
                .ifPresentOrElse(
                        movement -> movementRepository.delete(movement),
                        () -> {
                            throw new NoSuchElementException("The movement with the given id does not exist.");
                        }
                );
    }

}
