package com.esme.meetme.infrastructure;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.esme.meetme.domain.Match;
import com.esme.meetme.domain.Picture;
import com.esme.meetme.domain.Settings;
import com.esme.meetme.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    private MatchRepository matchRepository;
    private UserRepository userRepository;
    private PictureRepository pictureRepository;
    private SettingsRepository settingsRepository;

    public UserDao(MatchRepository matchRepository, UserRepository userRepository, PictureRepository pictureRepository, SettingsRepository settingsRepository){
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.settingsRepository = settingsRepository;
    }

    public List<User> findUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userEntity -> buildUser(
                    userEntity,
                    settingsRepository.findByUserEntity(userEntity),
                    pictureRepository.findByUserEntity(userEntity),
                    matchRepository.findByUserEntity(userEntity))
                )
                .collect(Collectors.toList());
    }

    public User findUser(UUID id) throws NotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return buildUser(
            userEntity,
            settingsRepository.findByUserEntity(userEntity),
            pictureRepository.findByUserEntity(userEntity),
            matchRepository.findByUserEntity(userEntity)
        );
    }

    public User createUser(User user) throws NotFoundException {
        UserEntity userEntity = userRepository.save(buildUserEntity(user));

//        settingsRepository.save(buildSettingsEntity(userEntity, user.getSettings()));
//
//        user.getPictures()
//                .stream()
//                .forEach(pic ->
//                        pictureRepository.save(buildPictureEntity(userEntity, pic)));
//
//        user.getMatchs()
//                .stream()
//                .forEach(match ->
//                        matchRepository.save(buildMatchEntity(userEntity, match)));

        return buildUser(
                userRepository.findById(userEntity.getId()).orElseThrow(NotFoundException::new),
                settingsRepository.findByUserEntity(userEntity),
                pictureRepository.findByUserEntity(userEntity),
                matchRepository.findByUserEntity(userEntity)
        );
    }

    public void deleteUser(UUID id) {
        userRepository.delete(userRepository.findById(id).get());
    }

    public User replaceUser(User user) {
        UserEntity userEntity = userRepository.save(buildUserEntity(user));
        return buildUser(userEntity, settingsRepository.findByUserEntity(userEntity), pictureRepository.findByUserEntity(userEntity), matchRepository.findByUserEntity(userEntity));
    }

    public void updateUser(User user){
        UserEntity userEntity = userRepository.save(buildUserEntity(user));
        buildUser(userEntity, settingsRepository.findByUserEntity(userEntity), pictureRepository.findByUserEntity(userEntity), matchRepository.findByUserEntity(userEntity));
    }

    private MatchEntity buildMatchEntity(UserEntity userEntity, Match match) {
        return MatchEntity.builder()
            .id(userEntity.getId())
            .date(match.getDate())
            .user(match.getUser())
            .result(match.getResult())
            .userEntity(userEntity)
        .build();
    }

    private PictureEntity buildPictureEntity(UserEntity userEntity, Picture pic) {
        return PictureEntity.builder()
            .id(userEntity.getId())
            .date(pic.getDate())
            .path(pic.getPath())
            .face(pic.getFace())
            .userEntity(userEntity)
        .build();
    }

    private SettingsEntity buildSettingsEntity(UserEntity userEntity, Settings settings){
        return SettingsEntity.builder()
            .id(userEntity.getId())
            .created(settings.getCreated())
            .email(settings.getEmail())
            .gender(settings.getGender())
            .attraction(settings.getAttraction())
            .coverSize(settings.getCoverSize())
            .latitude(settings.getLatitude())
            .longitude(settings.getLongitude())
            .ageMin(settings.getAgeMin())
            .ageMax(settings.getAgeMax())
            .userEntity(userEntity)
        .build();
    }

    private UserEntity buildUserEntity(User user){
        return UserEntity.builder()
            .id(user.getId())
            .name(user.getName())
            .city(user.getCity())
            .birthday(user.getBirthday())
            .description(user.getDescription())
        .build();

    }

    private User buildUser(UserEntity userEntity, SettingsEntity settingsEntity, List<PictureEntity> pictureEntities, List<MatchEntity> matchEntities){
        return User.builder()
            .id(userEntity.getId())

            .name(userEntity.getName())
            .city(userEntity.getCity())
            .birthday(userEntity.getBirthday())
            .description(userEntity.getDescription())

//            .settings(
//                Settings.builder()
//                    .id(userEntity.getId())
//                    .created(settingsEntity.getCreated())
//                    .email(settingsEntity.getEmail())
//                    .gender(settingsEntity.getGender())
//                    .attraction(settingsEntity.getAttraction())
//                    .coverSize(settingsEntity.getCoverSize())
//                    .latitude(settingsEntity.getLatitude())
//                    .longitude(settingsEntity.getLongitude())
//                    .ageMin(settingsEntity.getAgeMin())
//                    .ageMax(settingsEntity.getAgeMax())
//                .build()
//            )
//
//            .pictures(
//                pictureEntities.stream()
//                    .map(
//                        pictureEntity -> Picture.builder()
//                            .id(pictureEntity.getId())
//                            .date(pictureEntity.getDate())
//                            .path(pictureEntity.getPath())
//                            .face(pictureEntity.getFace())
//                        .build()
//                    )
//                    .collect(Collectors.toList())
//            )
//
//            .matchs(
//                matchEntities.stream()
//                .map(matchEntity -> Match.builder()
//                    .id(userEntity.getId())
//                    .date(matchEntity.getDate())
//                    .user(matchEntity.getUser())
//                    .result(matchEntity.getResult())
//                    .build()
//                )
//                .collect(Collectors.toList())
//            )
        .build();
    }
}
