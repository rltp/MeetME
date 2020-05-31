package com.esme.meetme.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PictureRepository extends CrudRepository<PictureEntity, UUID> {
    List<PictureEntity> findByUserEntityId(UUID userEntityId);
    List<PictureEntity> findByUserEntity(UserEntity userEntity);
}
