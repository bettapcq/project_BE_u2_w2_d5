package bettapcq.projectu2w2.services;

import bettapcq.projectu2w2.entities.Employee;
import bettapcq.projectu2w2.exceptions.BadRequestException;
import bettapcq.projectu2w2.payloads.NewEmployeeDTO;
import bettapcq.projectu2w2.repositories.EmployeesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public Employee addEmployee(NewEmployeeDTO payload) {
        Employee found = this.employeesRepository.findByUsername(payload.username());
        //controllo se esiste già un dipendente con stesso username.

        if (found != null)
            throw new BadRequestException("This username already exist with id: " + found.getEmployeeId());

        Employee newEmployee = new Employee(payload.username(), payload.name(), payload.surname(), payload.email());

        return this.employeesRepository.save(newEmployee);
    }
}

