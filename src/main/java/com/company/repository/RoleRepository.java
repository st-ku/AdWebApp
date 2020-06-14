package com.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<com.company.entity.Role, Long> {
    com.company.entity.Role findRoleByName(String roleName);

}
