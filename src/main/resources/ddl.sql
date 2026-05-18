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
-- Table `music_streaming_service_db`.`artists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`artists` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `country` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
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
  `artist_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `artist_id` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `albums_ibfk_1`
    FOREIGN KEY (`artist_id`)
    REFERENCES `music_streaming_service_db`.`artists` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
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
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`music_services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`music_services` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
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
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `payment_methods_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`))
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
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `playlists_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`))
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
  `artist_id` BIGINT NULL DEFAULT NULL,
  `album_id` BIGINT NULL DEFAULT NULL,
  `genre_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `artist_id` (`artist_id` ASC) VISIBLE,
  INDEX `album_id` (`album_id` ASC) VISIBLE,
  INDEX `genre_id` (`genre_id` ASC) VISIBLE,
  CONSTRAINT `songs_ibfk_1`
    FOREIGN KEY (`artist_id`)
    REFERENCES `music_streaming_service_db`.`artists` (`id`),
  CONSTRAINT `songs_ibfk_2`
    FOREIGN KEY (`album_id`)
    REFERENCES `music_streaming_service_db`.`albums` (`id`),
  CONSTRAINT `songs_ibfk_3`
    FOREIGN KEY (`genre_id`)
    REFERENCES `music_streaming_service_db`.`genres` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`reviews` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL DEFAULT NULL,
  `song_id` BIGINT NULL DEFAULT NULL,
  `rating` INT NULL DEFAULT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `song_id` (`song_id` ASC) VISIBLE,
  CONSTRAINT `reviews_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`),
  CONSTRAINT `reviews_ibfk_2`
    FOREIGN KEY (`song_id`)
    REFERENCES `music_streaming_service_db`.`songs` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_streaming_service_db`.`stream_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_streaming_service_db`.`stream_history` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NULL DEFAULT NULL,
  `song_id` BIGINT NULL DEFAULT NULL,
  `played_at` DATETIME NULL DEFAULT NULL,
  `listened_seconds` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `song_id` (`song_id` ASC) VISIBLE,
  CONSTRAINT `stream_history_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`),
  CONSTRAINT `stream_history_ibfk_2`
    FOREIGN KEY (`song_id`)
    REFERENCES `music_streaming_service_db`.`songs` (`id`))
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
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `subscriptions_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_streaming_service_db`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
