package com.esme.meetme.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<MatchEntity, UUID> {
    List<MatchEntity> findByUserEntityId(UUID userEntityId);
    List<MatchEntity> findByUserEntity(UserEntity userEntity);
}
