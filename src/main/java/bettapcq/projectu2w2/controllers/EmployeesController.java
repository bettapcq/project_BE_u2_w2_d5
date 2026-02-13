package bettapcq.projectu2w2.controllers;

import bettapcq.projectu2w2.entities.Employee;
import bettapcq.projectu2w2.exceptions.ValidationException;
import bettapcq.projectu2w2.payloads.NewEmployeeDTO;
import bettapcq.projectu2w2.services.EmployeesService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody @Validated NewEmployeeDTO payload, BindingResult valRes) {

        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }

        return this.employeesService.addEmployee(payload);
    }

}
