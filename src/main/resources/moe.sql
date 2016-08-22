-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema wecook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wecook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wecook` DEFAULT CHARACTER SET utf8 ;
USE `wecook` ;

-- -----------------------------------------------------
-- Table `wecook`.`cuisine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`cuisine` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cuisine` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`group` (
  `group_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE INDEX `group_id_UNIQUE` (`group_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `UK_oy92ak6u4rmbq75jgb14npht7` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`user` (
  `userid` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(32) NOT NULL,
  `username` VARCHAR(30) NOT NULL,
  `group` INT(11) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `userid` (`userid` ASC),
  INDEX `group_idx` (`group` ASC),
  CONSTRAINT `group`
    FOREIGN KEY (`group`)
    REFERENCES `wecook`.`group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`recipe` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `UK_rm1mlratj8yf3e1yxwk156x4p` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`dish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`dish` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `recipe` INT(11) NOT NULL,
  `cost` BIGINT(20) NULL DEFAULT NULL,
  `created` DATETIME NOT NULL,
  `created_by` INT(11) NULL DEFAULT NULL,
  `cuisine` INT(11) NULL DEFAULT NULL,
  `difficulty` INT(11) NULL DEFAULT NULL,
  `lastused` DATETIME NOT NULL,
  `servings` INT(11) NULL DEFAULT NULL,
  `time` INT(11) NULL DEFAULT NULL,
  `spicy` INT(11) NULL DEFAULT NULL,
  `burners` INT(11) NULL DEFAULT NULL,
  `oven` INT(11) NULL DEFAULT NULL,
  `count` INT(11) NULL DEFAULT NULL,
  `public` INT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `iddish` (`id` ASC),
  UNIQUE INDEX `name` (`name` ASC),
  UNIQUE INDEX `name_2` (`name` ASC),
  UNIQUE INDEX `UK_r7g2l08wdh3uv3gvurli4s1bx` (`name` ASC),
  INDEX `cuisine_idx` (`cuisine` ASC),
  INDEX `recipe_idx` (`recipe` ASC),
  INDEX `created_by_idx` (`created_by` ASC),
  CONSTRAINT `created_by`
    FOREIGN KEY (`created_by`)
    REFERENCES `wecook`.`user` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cuisine`
    FOREIGN KEY (`cuisine`)
    REFERENCES `wecook`.`cuisine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `recipe`
    FOREIGN KEY (`recipe`)
    REFERENCES `wecook`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`history` (
  `history_id` INT(11) NOT NULL AUTO_INCREMENT,
  `dish_history_id` INT(11) NOT NULL,
  `cook_date` DATETIME NOT NULL,
  `group` INT(11) NOT NULL,
  PRIMARY KEY (`history_id`),
  UNIQUE INDEX `id_UNIQUE` (`history_id` ASC),
  INDEX `dish_id_idx` (`dish_history_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`dish_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`dish_history` (
  `dish_history_id` INT(11) NOT NULL AUTO_INCREMENT,
  `history_id` INT(11) NOT NULL,
  `dish_id` INT(11) NOT NULL,
  PRIMARY KEY (`dish_history_id`),
  UNIQUE INDEX `id_UNIQUE` (`dish_history_id` ASC),
  INDEX `history_id_idx` (`history_id` ASC),
  INDEX `dish_id_idx` (`dish_id` ASC),
  CONSTRAINT `dish_id`
    FOREIGN KEY (`dish_id`)
    REFERENCES `wecook`.`dish` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `history_id`
    FOREIGN KEY (`history_id`)
    REFERENCES `wecook`.`history` (`history_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`ingredient_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`ingredient_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`ingredient` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `type` INT(11) NULL DEFAULT NULL COMMENT 'key for the type of ingredient it is (protein, veggie, grain, etc)',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ingredient_id` (`id` ASC),
  INDEX `type_idx` (`type` ASC),
  CONSTRAINT `type`
    FOREIGN KEY (`type`)
    REFERENCES `wecook`.`ingredient_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`recipe_step`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`recipe_step` (
  `recipe_step_id` INT(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` INT(11) NOT NULL,
  `step_number` INT(11) NOT NULL,
  `instructions` VARCHAR(600) NOT NULL,
  PRIMARY KEY (`recipe_step_id`),
  UNIQUE INDEX `recipe_step_id_UNIQUE` (`recipe_step_id` ASC),
  INDEX `recipe_id_idx` (`recipe_id` ASC),
  CONSTRAINT `recipe_id`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `wecook`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`unit_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`unit_type` (
  `unit_type_id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`unit_type_id`),
  UNIQUE INDEX `unit_type_id_UNIQUE` (`unit_type_id` ASC),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC),
  UNIQUE INDEX `UK_ity23v6wlt7f1f5cq4ahiubj2` (`type` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`unit` (
  `unit_id` INT(11) NOT NULL AUTO_INCREMENT,
  `unit_value` VARCHAR(45) NOT NULL,
  `unit_type` INT(11) NOT NULL,
  PRIMARY KEY (`unit_id`),
  UNIQUE INDEX `unit_id_UNIQUE` (`unit_id` ASC),
  UNIQUE INDEX `unit_value_UNIQUE` (`unit_value` ASC),
  UNIQUE INDEX `UK_4nse59d8eu0h1pu1i5vlig8hc` (`unit_value` ASC),
  INDEX `unit_type_f_idx` (`unit_type` ASC),
  CONSTRAINT `unit_type_f`
    FOREIGN KEY (`unit_type`)
    REFERENCES `wecook`.`unit_type` (`unit_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wecook`.`recipe_step_ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wecook`.`recipe_step_ingredient` (
  `ingredient_id` INT(11) NOT NULL,
  `unit` INT(11) NOT NULL,
  `quantity` DECIMAL(10,0) NOT NULL,
  `recipe_step_id` INT(11) NOT NULL,
  `recipe_id` INT(11) NOT NULL,
  `recipe_step_ingredient_id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`recipe_step_ingredient_id`),
  UNIQUE INDEX `recipe_step_ingredient_id_UNIQUE` (`recipe_step_ingredient_id` ASC),
  INDEX `ingredient_idx` (`ingredient_id` ASC),
  INDEX `recipe_step_id_idx` (`recipe_step_id` ASC),
  INDEX `recipe_id_idx` (`recipe_id` ASC),
  INDEX `recipe_id_fk_idx` (`recipe_id` ASC),
  INDEX `unit_fk_idx` (`unit` ASC),
  CONSTRAINT `ingredient`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `wecook`.`ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `recipe_id_fk`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `wecook`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `recipe_step_id`
    FOREIGN KEY (`recipe_step_id`)
    REFERENCES `wecook`.`recipe_step` (`recipe_step_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `unit_fk`
    FOREIGN KEY (`unit`)
    REFERENCES `wecook`.`unit` (`unit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
