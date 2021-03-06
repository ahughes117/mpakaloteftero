SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mpakaloteftero` ;
CREATE SCHEMA IF NOT EXISTS `mpakaloteftero` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mpakaloteftero` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE  TABLE IF NOT EXISTS `user` (
  `userID` BIGINT NOT NULL AUTO_INCREMENT ,
  `Email` VARCHAR(255) NOT NULL ,
  `Password` VARCHAR(600) NOT NULL ,
  `Name` VARCHAR(45) NOT NULL ,
  `Surname` VARCHAR(45) NOT NULL ,
  `Type` TINYINT NOT NULL DEFAULT 0 ,
  `DateCreated` TIMESTAMP NULL ,
  `LastLogin` TIMESTAMP NULL ,
  `LastIp` VARCHAR(16) NULL ,
  `_dateModified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`userID`) ,
  UNIQUE INDEX `Emai_UNIQUE` (`Email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expense` ;

CREATE  TABLE IF NOT EXISTS `expense` (
  `expenseID` BIGINT NOT NULL AUTO_INCREMENT ,
  `debiterID` BIGINT NOT NULL ,
  `crediterID` BIGINT NOT NULL ,
  `Name` VARCHAR(45) NOT NULL ,
  `Description` TEXT NULL ,
  `Price` DECIMAL(18,2) NOT NULL ,
  `Paid` TINYINT NOT NULL DEFAULT 0 ,
  `PaidRequest` TINYINT NOT NULL DEFAULT 0 ,
  `DateCreated` TIMESTAMP NULL ,
  `_dateModified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`expenseID`) ,
  INDEX `fk_crediter_user_idx` (`crediterID` ASC) ,
  INDEX `fk_debiter_user_idx` (`debiterID` ASC) ,
  CONSTRAINT `fk_crediter_user`
    FOREIGN KEY (`crediterID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debiter_user`
    FOREIGN KEY (`debiterID` )
    REFERENCES `user` (`userID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mpakaloteftero` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
