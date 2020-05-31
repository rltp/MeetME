package com.esme.meetme;

import com.esme.meetme.domain.Match;
import com.esme.meetme.domain.Picture;
import com.esme.meetme.domain.Settings;
import com.esme.meetme.infrastructure.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootApplication
@EnableScheduling

public class MeetmeApplication implements CommandLineRunner {

	private MatchRepository matchRepository;
	private UserRepository userRepository;
	private SettingsRepository settingsRepository;
	private PictureRepository pictureRepository;

	public MeetmeApplication(MatchRepository matchRepository, UserRepository userRepository, PictureRepository pictureRepository, SettingsRepository settingsRepository){
		this.matchRepository = matchRepository;
		this.userRepository = userRepository;
		this.pictureRepository = pictureRepository;
		this.settingsRepository = settingsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MeetmeApplication.class, args);
		System.out.println("Demorgordon is on fire");
	}

	@Override
	public void run(String... args){
		log.info("Users initilization");
		saveUser(
			UUID.fromString("bf986165-2e98-4cb9-a887-96ac1efe44dd"),
			"Emma",
			"Paris",
			new Date(),
			"Jeune ingénieure désirant trouver enfin l'amour",
			Settings.builder()
				.id(UUID.fromString("bf986165-2e98-4cb9-a887-96ac1efe44dd"))
				.created(new Date())
				.email("emma@esme.fr")
				.gender(1)
				.attraction(2)
				.coverSize(10)
				.longitude(48.8566)
				.latitude(2.3522)
				.ageMin(18)
				.ageMax(35)
			.build(),
			Arrays.asList(
					Picture.builder()
						.id(UUID.fromString("bf986165-2e98-4cb9-a887-96ac1efe44dd"))
						.date(new Date())
						.path("af986165-2e98.png")
						.face(Boolean.FALSE)
					.build(),
					Picture.builder()
						.id(UUID.fromString("bf986165-2e98-4cb9-a887-96ac1efe44dd"))
						.date(new Date())
						.path("bf986165-2e98.png")
						.face(Boolean.FALSE)
					.build()
			),
			Arrays.asList(
				Match.builder()
					.id(UUID.fromString("bf986165-2e98-4cb9-a887-96ac1efe44dd"))
					.date(new Date())
					.user(UUID.fromString("af986165-2e98-4cb9-a887-96ac1efe44dd"))
					.result(Boolean.FALSE)
				.build()
			)
		);

		this.userRepository.findAll().forEach(userEntity -> log.info(userEntity.getMatchEntities().get(0).getResult().toString()));
	}

	@Transactional
	private void saveUser(
			UUID id,

			String name,
			String city,
			Date birthday,
			String description,

			Settings settings,
			List<Picture> pictures,
			List<Match> matchs
	){

		UserEntity userEntity = this.userRepository.save(
			UserEntity.builder()
				.id(id)
				.name(name)
				.city(city)
				.birthday(birthday)
				.description(description)
			.build());

			this.settingsRepository.save(
				SettingsEntity
					.builder()
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
				.build()
			);

			pictures.stream().forEach(pic ->
				this.pictureRepository.save(
					PictureEntity.builder()
						.id(id)
						.userEntity(userEntity)
						.date(pic.getDate())
						.path(pic.getPath())
						.face(pic.getFace())
					.build()
				)
			);

			matchs.stream().forEach(match ->
				this.matchRepository.save(
					MatchEntity.builder()
						.id(id)
						.userEntity(userEntity)
						.date(match.getDate())
						.user(match.getUser())
						.result(match.getResult())
					.build()
				)
			);
	}
}