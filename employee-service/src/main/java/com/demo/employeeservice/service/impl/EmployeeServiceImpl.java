package com.demo.employeeservice.service.impl;

import com.demo.employeeservice.config.ApiConstants;
import com.demo.employeeservice.dto.APIResponseDto;
import com.demo.employeeservice.dto.DepartmentDto;
import com.demo.employeeservice.dto.EmployeeDto;
import com.demo.employeeservice.entity.Employee;
import com.demo.employeeservice.repository.EmployeeRepository;
import com.demo.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper mapper;
    private RestTemplate restTemplate;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = mapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        return mapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        // TODO CODE REFACTOR FOR CONST AND ALL THE METHOD GET EMPLOYEE
        String departmentApiUrl = ApiConstants.DEPARTMENT_API_URL;
        ResponseEntity<DepartmentDto> response = restTemplate.getForEntity(departmentApiUrl + employee.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = response.getBody();
        EmployeeDto employeeDto =mapper.map(employee, EmployeeDto.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
