-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema music_streaming_service_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema music_streaming_service_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `music_streaming_service_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `music_streaming_service_db` ;

-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`music_services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`music_services` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`artists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`artists` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `country` VARCHAR(100) NULL DEFAULT NULL,
  `music_service_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_artists_music_services1_idx` (`music_service_id` ASC) VISIBLE,
  CONSTRAINT `fk_artists_music_services1`
    FOREIGN KEY (`music_service_id`)
    REFERENCES `music_streaming_service_db`.`music_services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`albums`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`albums` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `release_date` DATE NULL DEFAULT NULL,
  `artist_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_albums_artists1_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `fk_albums_artists1`
    FOREIGN KEY (`artist_id`)
    REFERENCES `music_streaming_service_db`.`artists` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`genres` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `is_premium` TINYINT(1) NULL DEFAULT '0',
  `registration_date` DATE NULL DEFAULT NULL,
  `last_login` DATETIME NULL DEFAULT NULL,
  `music_service_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  INDEX `fk_users_music_services1_idx` (`music_service_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_music_services1`
    FOREIGN KEY (`music_service_id`)
    REFERENCES `music_streaming_service_db`.`music_services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`payment_methods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`payment_methods` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NULL DEFAULT NULL,
  `last_four` VARCHAR(4) NULL DEFAULT NULL,
  `is_default` TINYINT(1) NULL DEFAULT '0',
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_payment_methods_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_methods_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`playlists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`playlists` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `is_public` TINYINT(1) NULL DEFAULT '1',
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_playlists_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_playlists_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`songs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`songs` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `duration_seconds` DOUBLE NULL DEFAULT NULL,
  `release_date` DATE NULL DEFAULT NULL,
  `album_id` BIGINT NOT NULL,
  `genre_id` BIGINT NOT NULL,
  `playlist_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `playlist_id`),
  INDEX `fk_songs_albums1_idx` (`album_id` ASC) VISIBLE,
  INDEX `fk_songs_genres1_idx` (`genre_id` ASC) VISIBLE,
  INDEX `fk_songs_playlists1_idx` (`playlist_id` ASC) VISIBLE,
  CONSTRAINT `fk_songs_albums1`
    FOREIGN KEY (`album_id`)
    REFERENCES `music_streaming_service_db`.`albums` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_songs_genres1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `music_streaming_service_db`.`genres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_songs_playlists1`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `music_streaming_service_db`.`playlists` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`reviews` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL DEFAULT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `song_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `song_id`),
  INDEX `fk_reviews_songs1_idx` (`song_id` ASC) VISIBLE,
  CONSTRAINT `fk_reviews_songs1`
    FOREIGN KEY (`song_id`)
    REFERENCES `music_streaming_service_db`.`songs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`stream_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`stream_history` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `played_at` DATETIME NULL DEFAULT NULL,
  `listened_seconds` DOUBLE NULL DEFAULT NULL,
  `song_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `song_id`),
  INDEX `fk_stream_history_songs1_idx` (`song_id` ASC) VISIBLE,
  CONSTRAINT `fk_stream_history_songs1`
    FOREIGN KEY (`song_id`)
    REFERENCES `music_streaming_service_db`.`songs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`subscriptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`subscriptions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `plan` VARCHAR(50) NULL DEFAULT NULL,
  `price_per_month` DOUBLE NULL DEFAULT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_subscriptions_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_subscriptions_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
