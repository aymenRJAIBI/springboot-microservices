package com.demo.departementservice.controller;

import com.demo.departementservice.dto.DepartmentDto;
import com.demo.departementservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody  DepartmentDto dto) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(dto);

        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String depCode) {
        DepartmentDto  departmentDto = departmentService.getDepartmentByCode(depCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

}
