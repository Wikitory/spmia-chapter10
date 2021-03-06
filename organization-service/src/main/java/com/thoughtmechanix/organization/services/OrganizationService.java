package com.thoughtmechanix.organization.services;

import com.thoughtmechanix.organization.events.source.SimpleSourceBean;
import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationRepository orgRepository;

	@Autowired
	SimpleSourceBean simpleSourceBean;

	public Organization getOrg(String organizationId) {
		return orgRepository.findByOrganization(organizationId);
	}

	public void saveOrg(Organization org) {
		org.setOrganizationId(UUID.randomUUID().toString());

		orgRepository.save(org);
		simpleSourceBean.publishOrgChange("SAVE", org.getOrganizationId());
	}

	public void updateOrg(Organization org) {
		orgRepository.save(org);
		simpleSourceBean.publishOrgChange("UPDATE", org.getOrganizationId());

	}

	public void deleteOrg(Organization org) {
		orgRepository.delete(org);
		simpleSourceBean.publishOrgChange("DELETE", org.getOrganizationId());
	}
}
