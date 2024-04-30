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
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper mapper;
    private RestTemplate restTemplate;
    private WebClient webClient;





    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = mapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        return mapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = findEmployeeByIdOrThrow(employeeId);
        // TODO CODE REFACTOR FOR CONST AND ALL THE METHOD GET EMPLOYEE

        DepartmentDto departmentDto = getDepartmentDetailsWithWebClient(employee);

        EmployeeDto employeeDto =mapEmployeeToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }

    private Employee findEmployeeByIdOrThrow(Long employeeId){
        return employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Could not find employee with id " + employeeId));

    }
    private EmployeeDto mapEmployeeToDto(Employee employee) {
        return mapper.map(employee, EmployeeDto.class);
    }

    private DepartmentDto getDepartmentDetailsWithRestTemplate(Employee employee){
        String departmentApiUrl = ApiConstants.DEPARTMENT_API_URL;
        ResponseEntity<DepartmentDto> response = restTemplate.getForEntity(departmentApiUrl + employee.getDepartmentCode(), DepartmentDto.class);
       return  response.getBody();
    }
    private DepartmentDto getDepartmentDetailsWithWebClient(Employee employee){
        String departmentApiUrl = ApiConstants.DEPARTMENT_API_URL;
        DepartmentDto departmentDto = webClient.get().uri(departmentApiUrl+employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
        return departmentDto;
    }

}
