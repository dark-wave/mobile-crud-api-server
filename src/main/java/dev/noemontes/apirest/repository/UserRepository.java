package dev.noemontes.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import dev.noemontes.apirest.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{}
