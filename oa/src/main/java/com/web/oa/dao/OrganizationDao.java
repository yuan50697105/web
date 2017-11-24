package com.web.oa.dao;

import com.web.oa.bean.Organization;

public interface OrganizationDao {
    boolean save(Organization organization);

    boolean delete(Long id);

    boolean update(Organization organization);

    Organization getById(Long id);
}
