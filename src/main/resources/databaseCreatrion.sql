-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema expositions_calendar
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `expositions_calendar` ;

-- -----------------------------------------------------
-- Schema expositions_calendar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `expositions_calendar` DEFAULT CHARACTER SET utf8 ;
USE `expositions_calendar` ;

-- -----------------------------------------------------
-- Table `expositions_calendar`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expositions_calendar`.`roles` ;

CREATE TABLE IF NOT EXISTS `expositions_calendar`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expositions_calendar`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expositions_calendar`.`users` ;

CREATE TABLE IF NOT EXISTS `expositions_calendar`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `surname` VARCHAR(255) NOT NULL,
  `role_id` INT NOT NULL DEFAULT 2,
  PRIMARY KEY (`id`, `role_id`),
  INDEX `fk_users_roles_idx` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`role_id`)
    REFERENCES `expositions_calendar`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expositions_calendar`.`halls`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expositions_calendar`.`halls` ;

CREATE TABLE IF NOT EXISTS `expositions_calendar`.`halls` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `house_number` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `house_number_UNIQUE` (`house_number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expositions_calendar`.`expositions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expositions_calendar`.`expositions` ;

CREATE TABLE IF NOT EXISTS `expositions_calendar`.`expositions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `theme` VARCHAR(255) NOT NULL,
  `start_time` DATE NOT NULL,
  `finish_time` DATE NOT NULL,
  `ticket_price` DECIMAL(6,2) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `hall_id` INT NOT NULL,
  PRIMARY KEY (`id`, `hall_id`),
  INDEX `fk_expositions_halls1_idx` (`hall_id` ASC) VISIBLE,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  CONSTRAINT `fk_expositions_halls1`
    FOREIGN KEY (`hall_id`)
    REFERENCES `expositions_calendar`.`halls` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `expositions_calendar`.`payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expositions_calendar`.`payments` ;

CREATE TABLE IF NOT EXISTS `expositions_calendar`.`payments` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `payment_time` TIMESTAMP NOT NULL,
  `status` ENUM('Passed', 'Failed') NOT NULL,
  `tickets_amount` INT UNSIGNED NULL DEFAULT NULL,
  `price` DECIMAL(11,2) NOT NULL,
  `user_id` BIGINT NOT NULL,
  `exposition_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `exposition_id`),
  INDEX `fk_payments_expositions1_idx` (`exposition_id` ASC) VISIBLE,
  INDEX `fk_payments_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_payments_expositions1`
    FOREIGN KEY (`exposition_id`)
    REFERENCES `expositions_calendar`.`expositions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `expositions_calendar`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expositions_calendar`.`tickets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expositions_calendar`.`tickets` ;

CREATE TABLE IF NOT EXISTS `expositions_calendar`.`tickets` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `valid_date` DATE NOT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  `payment_id` BIGINT NULL DEFAULT NULL,
  `exposition_id` BIGINT NOT NULL,
  `hall_id` INT NOT NULL,
  PRIMARY KEY (`id`, `exposition_id`, `hall_id`),
  INDEX `fk_tickets_payments1_idx` (`payment_id` ASC) VISIBLE,
  INDEX `fk_tickets_expositions1_idx` (`exposition_id` ASC, `hall_id` ASC) VISIBLE,
  INDEX `fk_tickets_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_tickets_payments1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `expositions_calendar`.`payments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_expositions1`
    FOREIGN KEY (`exposition_id` , `hall_id`)
    REFERENCES `expositions_calendar`.`expositions` (`id` , `hall_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `expositions_calendar`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
