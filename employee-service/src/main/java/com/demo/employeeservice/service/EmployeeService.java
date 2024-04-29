package com.demo.employeeservice.service;

import com.demo.employeeservice.dto.APIResponseDto;
import com.demo.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employee);
    APIResponseDto getEmployeeById(Long employeeId);

}
