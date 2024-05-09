package com.demo.organizationservice.service.impl;

import com.demo.organizationservice.dto.OrganizationDto;
import com.demo.organizationservice.entity.Organization;
import com.demo.organizationservice.repository.OrganizationRepository;
import com.demo.organizationservice.service.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository  organizationRepository;
    private ModelMapper mapper;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = mapper.map(organizationDto, Organization.class);
        Organization savedOrg = organizationRepository.save(organization);
        return mapper.map(savedOrg, OrganizationDto.class);

    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization org = organizationRepository.findByOrganizationCode(code);
        if(org==null)
        {
            throw new EntityNotFoundException("Organization with code " + code + " not found");
        }
        return mapper.map(org,OrganizationDto.class);

    }

}
