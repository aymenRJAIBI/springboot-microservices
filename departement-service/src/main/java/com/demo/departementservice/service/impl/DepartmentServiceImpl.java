package com.demo.departementservice.service.impl;

import com.demo.departementservice.dto.DepartmentDto;
import com.demo.departementservice.entity.Department;
import com.demo.departementservice.repository.DepartmentRepository;
import com.demo.departementservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private ModelMapper mapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = mapper.map(departmentDto, Department.class);
        Department savedDep = departmentRepository.save(department);
        return mapper.map(savedDep, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department dep = departmentRepository.findByDepartmentCode(code);
        return mapper.map(dep,DepartmentDto.class);
    }
}
