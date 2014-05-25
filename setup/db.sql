CREATE TABLE `users` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`login` VARCHAR(100) NOT NULL,
`password` CHAR(32) NOT NULL,
`salt` CHAR(32) NOT NULL,
`active` BOOLEAN NOT NULL,
`created_at` DATETIME NOT NULL,
UNIQUE(`login`)
)engine=innodb;

CREATE TABLE `user_tokens` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`user_id` INT UNSIGNED NOT NULL,
`token` CHAR(32) NOT NULL,
`ip` VARCHAR(100) NOT NULL,
`created_at` DATETIME NOT NULL,
CONSTRAINT `user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE
) engine=innodb;

CREATE TABLE `maps` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(100) NOT NULL,
`image` VARCHAR(100) NOT NULL,
`display_order` INT UNSIGNED NOT NULL,
INDEX(`display_order`)
);
INSERT INTO `maps`
(`name`,`image`,`display_order`)
VALUES
("Dead Center", "dead-center.jpg", 1),
("The Passing", "the-passing.jpg", 2),
("Dark Carnival", "dark-carnival.jpg", 3),
("Swamp Fever", "swamp-fever.jpg", 4),
("Hard Rain", "hard-rain.jpg", 5),
("The Parish", "the-parish.jpg", 6),
("The Sacrifice", "the-sacrifice.jpg", 7),
("No Mercy", "no-mercy.jpg", 8),
("Crash Course", "crash-course.jpg", 9),
("Death Toll", "death-toll.jpg", 10),
("Dead Air", "dead-air.jpg", 11),
("Blood Harvest", "blood-harvest.jpg", 12),
("Cold Stream", "cold-stream.jpg", 13);

CREATE TABLE `difficulty_levels` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(100) NOT NULL,
`display_order` INT UNSIGNED NOT NULL,
INDEX(`display_order`)
);
INSERT INTO `difficulty_levels`
(`name`, `display_order`)
VALUES
("Easy", 1),
("Normal", 2),
("Advanced", 3),
("Expert", 4);

CREATE TABLE `match_types`(
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`identifier` VARCHAR(100) NOT NULL,
`name` VARCHAR(100) NOT NULL,
`display_order` INT UNSIGNED NOT NULL,
UNIQUE(`identifier`)
)engine=innodb;
INSERT INTO `match_types`
(`id`, `identifier`, `name`, `display_order`)
VALUES
(1, "campaign", "Campaign", 1);

DROP TABLE IF EXISTS `matches`;
CREATE TABLE `matches` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`match_type_id` INT UNSIGNED NOT NULL,
`owner_id` INT UNSIGNED NOT NULL,
`map_id` INT UNSIGNED NOT NULL,
`played_at` DATETIME NOT NULL,
`active` BOOLEAN NOT NULL,
CONSTRAINT `match_type_id_fkey` FOREIGN KEY (`match_type_id`) REFERENCES `match_types` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `owner_id_fkey` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `map_id_fkey` FOREIGN KEY (`map_id`) REFERENCES `maps` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE
)engine=innodb;

CREATE TABLE `matches_campaign` (
`match_id` INT UNSIGNED NOT NULL PRIMARY KEY,
`time` INT UNSIGNED NOT NULL,
`difficulty_id` INT UNSIGNED NOT NULL,
`restarts` INT UNSIGNED NOT NULL,
CONSTRAINT `match_id_fkey` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `difficulty_id_fkey` FOREIGN KEY (`difficulty_id`) REFERENCES `difficulty_levels` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE
)engine=innodb;
