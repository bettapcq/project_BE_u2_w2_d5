package bettapcq.projectu2w2.repositories;

import bettapcq.projectu2w2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {
    public Employee findByUsername(String username);
}
