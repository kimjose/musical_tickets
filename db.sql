-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.35-0ubuntu0.23.10.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table ticketer.musicals
DROP TABLE IF EXISTS `musicals`;
CREATE TABLE IF NOT EXISTS `musicals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `category` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `runtime` int NOT NULL DEFAULT '0',
  `minage` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ticketer.musicals: ~0 rows (approximately)
INSERT INTO `musicals` (`id`, `title`, `category`, `runtime`, `minage`) VALUES
	(1, 'The Lion King', 'Disney Shows, Family and Kids', 130, 6),
	(2, 'Frozen', 'Disney shows, Family and Kids, Weekday Matinees', 145, 6);

-- Dumping structure for table ticketer.schedules
DROP TABLE IF EXISTS `schedules`;
CREATE TABLE IF NOT EXISTS `schedules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL,
  `musical_id` int NOT NULL,
  `venue_id` int NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_musical_id` (`musical_id`),
  KEY `fk_venue_id` (`venue_id`),
  CONSTRAINT `fk_musical_id` FOREIGN KEY (`musical_id`) REFERENCES `musicals` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_venue_id` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ticketer.schedules: ~5 rows (approximately)
INSERT INTO `schedules` (`id`, `date_time`, `musical_id`, `venue_id`, `price`) VALUES
	(1, '2023-12-20 14:31:49', 2, 1, 12),
	(2, '2023-12-16 14:32:31', 2, 1, 10),
	(3, '2023-12-15 14:32:52', 1, 1, 12),
	(4, '2023-12-17 14:33:15', 1, 3, 15),
	(5, '2023-12-24 14:33:34', 2, 3, 18);

-- Dumping structure for table ticketer.tickets
DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `musical_id` int NOT NULL,
  `venue_id` int NOT NULL,
  `type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `seat_number` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `time_slot` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ticketer.tickets: ~1 rows (approximately)
INSERT INTO `tickets` (`id`, `musical_id`, `venue_id`, `type`, `seat_number`, `time_slot`, `price`) VALUES
	(1, 1, 1, 'Adult', 'A1', '2023-12-20 17:31:49', 12);

-- Dumping structure for table ticketer.venues
DROP TABLE IF EXISTS `venues`;
CREATE TABLE IF NOT EXISTS `venues` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0',
  `seats` text COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ticketer.venues: ~0 rows (approximately)
INSERT INTO `venues` (`id`, `name`, `seats`) VALUES
	(1, 'Lyceum Theatre', 'A1,A2,A3,B1,B2,B3,C1,C2,C3'),
	(2, 'Royal Drury', 'A1,A2,A3,B1,B2,B3,C1,C2,C3'),
	(3, 'Nairobi Cinema', 'A1,A2,A3,A4,B1,B2,B3,B4,C1,C2,C3,C4');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
