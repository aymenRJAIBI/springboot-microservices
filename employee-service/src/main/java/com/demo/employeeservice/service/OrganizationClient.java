package com.demo.employeeservice.service;

import com.demo.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ORGANIZATION-SERVICE")
public interface OrganizationClient {
    @GetMapping("api/organizations/{code}")
    OrganizationDto getOrganization(@PathVariable("code") String organizationCode);
}
