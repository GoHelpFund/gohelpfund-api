package com.gohelpfund.api.v1.authentication.repository;


import com.gohelpfund.api.v1.authentication.model.UserOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgUserRepository extends CrudRepository<UserOrganization,String>  {
    public UserOrganization findByUserName(String userName);
}
