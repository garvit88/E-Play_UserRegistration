package com.stackroute.eplay.userregistration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.eplay.userregistration.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}