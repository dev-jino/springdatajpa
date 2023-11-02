package com.example.springdatajpa.service;

import com.example.springdatajpa.domain.Employee;
import com.example.springdatajpa.domain.EmployeeUpdateDto;
import com.example.springdatajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Transactional(readOnly = true)
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  public Employee addEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Transactional(readOnly = true)
  public Optional<Employee> getEmployee(String id) { // ontional test
    return employeeRepository.findById(id);
  }

  public String updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
    Optional<Employee> findEmployee = employeeRepository.findById(employeeUpdateDto.getEmpId());
    Employee employee = findEmployee.get();
    employee.setDeptId(employeeUpdateDto.getDeptId());
    employee.setSalary(employeeUpdateDto.getSalary());
    Employee save = employeeRepository.save(employee);

    return "수정완료";
  }

  public String deleteEmployee(String empId){
    employeeRepository.deleteById(empId);
    
    return "삭제완료";
  }
}
