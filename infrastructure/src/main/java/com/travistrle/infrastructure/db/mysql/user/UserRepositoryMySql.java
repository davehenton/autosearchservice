package com.travistrle.infrastructure.db.mysql.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryMySql extends JpaRepository<UserEntity, String> {

}
