-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 18, 2024 at 07:40 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `campusapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(255) NOT NULL,
  `description` text,
  `event_date` date NOT NULL,
  `event_location` varchar(255) NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `event_name`, `description`, `event_date`, `event_location`) VALUES
(1, 'Test Event', 'this is test event', '2024-10-12', 'text place'),
(5, 'New Event', 'This is Test Event', '2024-10-12', 'canteent'),
(6, 'spandan', 'event ', '2024-10-15', 'seminar hall'),
(7, 'Dance Event', 'This is Dance Event for EveryOne', '2024-10-22', 'Seminar hall');

-- --------------------------------------------------------

--
-- Table structure for table `registrations`
--

DROP TABLE IF EXISTS `registrations`;
CREATE TABLE IF NOT EXISTS `registrations` (
  `registration_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `event_id` int DEFAULT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`registration_id`),
  KEY `user_id` (`user_id`),
  KEY `event_id` (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `registrations`
--

INSERT INTO `registrations` (`registration_id`, `user_id`, `event_id`, `registration_date`) VALUES
(1, 1, 1, '2024-09-28 20:29:16'),
(2, 1, 1, '2024-09-28 20:41:15'),
(3, 2, 1, '2024-09-28 20:55:30'),
(4, 1, 3, '2024-09-28 20:57:11'),
(5, 3, 3, '2024-09-29 06:35:47'),
(6, 1, 4, '2024-10-02 09:20:16'),
(7, 2, 1, '2024-10-02 17:40:31'),
(8, 4, 1, '2024-10-02 19:00:31'),
(9, 4, 5, '2024-10-02 19:01:54'),
(10, 5, 5, '2024-10-03 05:59:29'),
(11, 6, 6, '2024-10-03 09:06:42'),
(12, 2, 6, '2024-10-03 09:07:32'),
(13, 8, 7, '2024-10-16 20:39:40');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` enum('Male','Female','Other') DEFAULT NULL,
  `branch` enum('IT','CSE','CS','EXTC') DEFAULT NULL,
  `college_year` enum('First','Second','Third','Final') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `email`, `gender`, `branch`, `college_year`) VALUES
(1, 'Rishabh', '123', 'rishabh@gmail.com', 'Male', 'IT', 'Second'),
(2, 'Tejas', '123', 'tejas@gmail.com', 'Female', 'CSE', 'Third'),
(3, 'Pranesh', '123', 'pranesh@gmail.com', 'Female', 'EXTC', 'Third'),
(4, 'TestUser', '123', 'teast@test.com', 'Male', 'EXTC', 'Final'),
(5, 'Mudit', '123', 'mudit@gmail.com', 'Male', 'CS', 'Second'),
(6, 'newtestuser', '123', 'testuser@gmail.com', 'Female', 'IT', 'Second'),
(8, 'Bheem', '123', 'bheem@gmail.com', 'Male', 'CSE', 'Third');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
