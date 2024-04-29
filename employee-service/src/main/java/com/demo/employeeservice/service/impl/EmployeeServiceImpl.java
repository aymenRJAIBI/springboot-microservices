package com.demo.employeeservice.service.impl;

import com.demo.employeeservice.dto.EmployeeDto;
import com.demo.employeeservice.entity.Employee;
import com.demo.employeeservice.repository.EmployeeRepository;
import com.demo.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper mapper;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = mapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        return mapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow();

        return mapper.map(employee, EmployeeDto.class);
    }
}
