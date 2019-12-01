CREATE TABLE `user`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(25) NULL,
  `description` TEXT NULL,
  `gmt_create` BIGINT NULL,
  `gmt_modified` BIGINT NULL,
  `creator` INT NULL,
  `comment_count` INT NULL DEFAULT 0,
  `view_count` INT NULL DEFAULT 0,
  `like_count` INT NULL DEFAULT 0,
  `tag` VARCHAR(256) NULL,
  PRIMARY KEY (`id`));
