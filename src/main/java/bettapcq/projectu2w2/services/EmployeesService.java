package bettapcq.projectu2w2.services;

import bettapcq.projectu2w2.entities.Employee;
import bettapcq.projectu2w2.exceptions.BadRequestException;
import bettapcq.projectu2w2.exceptions.NotFoundException;
import bettapcq.projectu2w2.payloads.EmployeeDTO;
import bettapcq.projectu2w2.repositories.EmployeesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public Employee addEmployee(EmployeeDTO payload) {
        Employee found = this.employeesRepository.findByUsername(payload.username());
        //controllo se esiste già un dipendente con stesso username.

        if (found != null)
            throw new BadRequestException("This username already exist with id: " + found.getEmployeeId());

        Employee newEmployee = new Employee(payload.username(), payload.name(), payload.surname(), payload.email());

        return this.employeesRepository.save(newEmployee);
    }

    public Page<Employee> findAll(int page, int size, String orderBy) {
        if (page < 0) page = 0;
        if (size > 100 || size < 0) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return this.employeesRepository.findAll(pageable);
    }


    public Employee findById(Long employeeId) {
        return this.employeesRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
    }


    public Employee findByIdAndEdit(EmployeeDTO payload, Long employeeId) {
        Employee found = this.employeesRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));

        found.setUsername(payload.username());
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());

        return this.employeesRepository.save(found);
    }

    public void findByIdAndDelete(Long employeeId) {
        Employee found = this.employeesRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));

        this.employeesRepository.delete(found);
    }

    //upload img

}

