package com.esme.meetme.infrastructure;

import com.esme.meetme.domain.Settings;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SettingsRepository extends CrudRepository<SettingsEntity, UUID> {
    SettingsEntity findByUserEntityId(UUID userEntityId);
    SettingsEntity findByUserEntity(UserEntity userEntity);
}