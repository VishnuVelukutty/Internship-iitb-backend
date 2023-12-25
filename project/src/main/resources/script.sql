CREATE DATABASE IF NOT EXISTS CourseDetail;
USE CourseDetail;

CREATE TABLE IF NOT EXISTS `course_list` (
  `course_id` INT AUTO_INCREMENT NOT NULL,
  `course_code` varchar(255) NOT NULL,
  `course_desc` varchar(255) DEFAULT NULL,
  `course_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `course_instance` (
  `instance_id` INT AUTO_INCREMENT NOT NULL,
  `course_id` INT NOT NULL,
  `course_year` INT NOT NULL,
  `course_semester` INT NOT NULL,
  PRIMARY KEY (`instance_id`),
  FOREIGN KEY (`course_id`) REFERENCES `course_list`(`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `login_details` (
  `login_id` INT AUTO_INCREMENT NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_pass`varchar(255) NOT NULL,
  `user_type` CHAR(1) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;