package bettapcq.projectu2w2.controllers;

import bettapcq.projectu2w2.entities.Employee;
import bettapcq.projectu2w2.exceptions.ValidationException;
import bettapcq.projectu2w2.payloads.EmployeeDTO;
import bettapcq.projectu2w2.services.EmployeesService;
import org.springframework.data.domain.Page;
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

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody @Validated EmployeeDTO payload, BindingResult valRes) {

        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }

        return this.employeesService.addEmployee(payload);
    }

    // GET ALL
    @GetMapping
    public Page<Employee> findAllEmployees(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(defaultValue = "surname") String orderBy) {
        return this.employeesService.findAll(page, size, orderBy);
    }

    //GET BY ID
    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Employee findById(@PathVariable Long employeeId) {
        return this.employeesService.findById(employeeId);
    }

    //PUT
    @PutMapping("/{employeeId}")
    public Employee findByIdAndEdit(@PathVariable Long employeeId, @RequestBody @Validated EmployeeDTO payload, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errList);
        }

        return this.employeesService.findByIdAndEdit(payload, employeeId);
    }

    //DELETE

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdANdDelete(@PathVariable Long employeeId) {
        this.employeesService.findByIdAndDelete(employeeId);
    }

}
