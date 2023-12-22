package com.nisum.repository;

import com.nisum.model.entity.User;

public interface IUserRepository extends IGenericRepository<User, Long> {

  boolean existsByEmail(String email);

}
