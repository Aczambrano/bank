package com.devsu.bankaccount.application.port.ouput;

import com.devsu.bankaccount.domain.model.Movement;

import java.util.List;
import java.util.Optional;

public interface IMovementrepository {
    List<Movement> findAll();
    Optional<Movement> findById(Integer id);
    Optional<Movement>  findByAccountId(Integer accountId);
    Movement save(Movement movement);
    Movement update(Movement movement);
    Movement delete(Movement movement);
}
