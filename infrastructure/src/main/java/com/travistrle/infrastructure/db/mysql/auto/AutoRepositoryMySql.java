package com.travistrle.infrastructure.db.mysql.auto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepositoryMySql extends JpaRepository<AutoEntity, String> {

}
