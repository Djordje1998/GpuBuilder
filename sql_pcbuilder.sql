/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.24-MariaDB : Database - spring_projekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring_projekat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `spring_projekat`;

/*Table structure for table `app_user` */

DROP TABLE IF EXISTS `app_user`;

CREATE TABLE `app_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `profile` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `user_profile_fk` (`profile`),
  CONSTRAINT `user_profile_fk` FOREIGN KEY (`profile`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `app_user` */

insert  into `app_user`(`id`,`username`,`user_password`,`first_name`,`last_name`,`email`,`profile`) values 
(14,'admin','$2a$10$rvC1A308Qs6ywl3HR8VTuu3Sf4NYOdYF425GojtOQvy1u.Vl/1dL.','Marko','Markovic','mare7@gmail.com',2),
(15,'user','$2a$10$Sp1E5J.2z.1XRbGRKxES/OgRD/Ns36UynWpcpLrxtnSsJjBfbxVYa','User','User','user@gmail.com',1),
(26,'test','$2a$10$m/GXwHCjZezYTlddI7IGEOV7GVA4Wz3wqJrU9CgGLqBmepoRe3z3G','Test','Test','test@gmail.com',2);

/*Table structure for table `benchmark` */

DROP TABLE IF EXISTS `benchmark`;

CREATE TABLE `benchmark` (
  `BenchmarkID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `DatumBenchmarka` date NOT NULL,
  `BodoviPrvogRacunara` decimal(10,2) unsigned NOT NULL,
  `BodoviDrugogRacunara` decimal(10,2) unsigned NOT NULL,
  `RacunarID1` bigint(20) unsigned NOT NULL,
  `RacunarID2` bigint(20) unsigned NOT NULL,
  `StressTestID` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`BenchmarkID`),
  KEY `RacunarID1` (`RacunarID1`),
  KEY `RacunarID2` (`RacunarID2`),
  KEY `StressTestID` (`StressTestID`),
  CONSTRAINT `benchmark_racunar1_fk` FOREIGN KEY (`RacunarID1`) REFERENCES `racunar` (`RacunarID`) ON DELETE CASCADE,
  CONSTRAINT `benchmark_racunar2_fk` FOREIGN KEY (`RacunarID2`) REFERENCES `racunar` (`RacunarID`) ON DELETE CASCADE,
  CONSTRAINT `benchmark_stresstest_fk` FOREIGN KEY (`StressTestID`) REFERENCES `stresstest` (`StressTestID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `benchmark` */

insert  into `benchmark`(`BenchmarkID`,`DatumBenchmarka`,`BodoviPrvogRacunara`,`BodoviDrugogRacunara`,`RacunarID1`,`RacunarID2`,`StressTestID`) values 
(57,'2022-11-12',2819.46,3059.21,78,79,5),
(59,'2022-11-13',4505.93,8121.36,77,81,3);

/*Table structure for table `komponente` */

DROP TABLE IF EXISTS `komponente`;

CREATE TABLE `komponente` (
  `KomponentaID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NazivKomponente` varchar(50) NOT NULL,
  `TipKomponenteID` bigint(20) unsigned NOT NULL,
  `Takt` decimal(10,2) NOT NULL,
  `Opis` varchar(250) NOT NULL,
  `Cena` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`KomponentaID`),
  KEY `TipKomponenteID` (`KomponentaID`),
  KEY `komponente_tipkomponente_fk` (`TipKomponenteID`),
  KEY `RacunarID` (`Opis`),
  CONSTRAINT `komponente_tipkomponente_fk` FOREIGN KEY (`TipKomponenteID`) REFERENCES `tipkomponente` (`TipKomponenteID`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

/*Data for the table `komponente` */

insert  into `komponente`(`KomponentaID`,`NazivKomponente`,`TipKomponenteID`,`Takt`,`Opis`,`Cena`) values 
(98,'Intel Core i9-13900K',2,5.80,'Professional Intel processor for heavy workload and complex rendering.',589.00),
(99,'Intel Core i7-13700K',2,5.40,'High-end Intel processor for games with realistic graphic.',419.00),
(101,'GeForce RTX 3080 Ti',1,1.67,'Best ray-tracing graphic card for realistic games.',1199.00),
(102,'GeForce RTX 4080',1,2.51,'Newest ray-tracing hard for heavy model rendering.',1199.00),
(103,'AMD RX 7900 XT',1,1.50,'AMD high-end graphic card for realistic games and editing.',899.00),
(104,'AMD RX 6700 XT',1,2.58,'Middle-end AMD graphic card for games and daily usage.',479.00),
(105,'Ryzen 7 5800X',2,4.70,'4th generation of Zen architecture best for cpu intensive work',449.00),
(107,'Ryzen 5 5600X',2,4.60,'Best buy cpu for gaming and daily usage.',299.00),
(108,'Vengeance 16GB ',3,3.60,'Dual-channel DDR4 memory with passive cooling.',129.00),
(109,'Kingston Fury 32GB',3,3.20,'RGB memory with high capacity best for rendering images and photoshop.',219.00),
(110,'Samsung 970 EVO',4,3.50,'High speed SSD with 1TB of space, best for transferring large files.',199.00),
(111,'Crucial P5 Plus',4,6.60,'500GB high speed SDD for gaming and rendering.',139.00),
(112,'Toshiba 2TB Canvio',5,0.50,'High capacity memory for low price best for storage large files.',99.00),
(113,'Intel i3 6400',2,3.40,'Low end processor for basic usage.',195.00);

/*Table structure for table `ocena` */

DROP TABLE IF EXISTS `ocena`;

CREATE TABLE `ocena` (
  `KomponentaID` bigint(20) unsigned NOT NULL,
  `StressTestID` bigint(20) unsigned NOT NULL,
  `VrednostOcene` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`KomponentaID`,`StressTestID`),
  KEY `StressTestID` (`StressTestID`),
  KEY `KomponentaID` (`KomponentaID`),
  CONSTRAINT `ocena_komponenta_fk` FOREIGN KEY (`KomponentaID`) REFERENCES `komponente` (`KomponentaID`) ON DELETE CASCADE,
  CONSTRAINT `ocena_test_fk` FOREIGN KEY (`StressTestID`) REFERENCES `stresstest` (`StressTestID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ocena` */

insert  into `ocena`(`KomponentaID`,`StressTestID`,`VrednostOcene`) values 
(98,3,356.20),
(98,6,328.80),
(98,7,164.40),
(105,6,213.72),
(105,7,106.86),
(110,5,143.85),
(112,3,320.58);

/*Table structure for table `racunar` */

DROP TABLE IF EXISTS `racunar`;

CREATE TABLE `racunar` (
  `RacunarID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NazivRacunara` varchar(100) NOT NULL,
  `Namena` varchar(255) NOT NULL,
  `Garancija` date NOT NULL,
  `UkupnaCena` decimal(10,2) unsigned NOT NULL,
  PRIMARY KEY (`RacunarID`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

/*Data for the table `racunar` */

insert  into `racunar`(`RacunarID`,`NazivRacunara`,`Namena`,`Garancija`,`UkupnaCena`) values 
(77,'Prime Pro Torpedo','Desktop PC with high end Nvidia GPU and best Intel core CPU best for gaming and intensive workload like rendering and photoshop','2024-11-12',2274.00),
(78,'Rendering Workbench ','Best for rendering 4K videos and photos on newest software, with high core count CPU and large memory capacity.','2025-11-12',4120.00),
(79,'Budget PC','Low-end inexpensive PC for daily usage and low graphic games','2023-11-12',1006.00),
(80,'Blue Lightning','Powerful gaming PC with intel CPU and Nvidia graphic card, best for games that require high FPS.','2024-11-12',1946.00),
(81,'Red Lightning','Optimal gaming PC for newest games on high settings with AMD CPU and graphic card, ','2025-11-12',3293.00),
(83,'PC','Opis','2022-12-09',0.00);

/*Table structure for table `stavkeracunara` */

DROP TABLE IF EXISTS `stavkeracunara`;

CREATE TABLE `stavkeracunara` (
  `RacunarID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `RB` bigint(20) unsigned NOT NULL,
  `KomponentaID` bigint(20) unsigned NOT NULL,
  `CenaKomada` decimal(10,0) unsigned NOT NULL,
  `Kolicina` int(11) NOT NULL,
  `UkupnaCena` decimal(10,0) NOT NULL,
  `DatumKreiranja` date NOT NULL,
  `Kreirao` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`RacunarID`,`RB`),
  KEY `stavka_komponentaID_fk` (`KomponentaID`),
  KEY `stavka_kreirao_fk` (`Kreirao`),
  CONSTRAINT `stavka_komponentaID_fk` FOREIGN KEY (`KomponentaID`) REFERENCES `komponente` (`KomponentaID`),
  CONSTRAINT `stavka_kreirao_fk` FOREIGN KEY (`Kreirao`) REFERENCES `app_user` (`id`),
  CONSTRAINT `stavka_racunarID_fk` FOREIGN KEY (`RacunarID`) REFERENCES `racunar` (`RacunarID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

/*Data for the table `stavkeracunara` */

insert  into `stavkeracunara`(`RacunarID`,`RB`,`KomponentaID`,`CenaKomada`,`Kolicina`,`UkupnaCena`,`DatumKreiranja`,`Kreirao`) values 
(77,1,102,1199,1,1199,'2022-11-12',14),
(77,2,99,419,1,419,'2022-11-12',14),
(77,3,110,199,2,398,'2022-11-12',14),
(77,4,108,129,2,258,'2022-11-12',14),
(78,1,98,589,1,589,'2022-11-12',14),
(78,2,112,99,3,297,'2022-11-12',14),
(78,3,110,199,2,398,'2022-11-12',14),
(78,4,102,1199,2,2398,'2022-11-12',14),
(78,5,109,219,2,438,'2022-11-12',14),
(79,1,108,129,1,129,'2022-11-12',14),
(79,2,112,99,1,99,'2022-11-12',14),
(79,3,107,299,1,299,'2022-11-12',14),
(79,4,104,479,1,479,'2022-11-12',14),
(80,1,99,419,1,419,'2022-11-12',14),
(80,2,101,1199,1,1199,'2022-11-12',14),
(80,3,108,129,1,129,'2022-11-12',14),
(80,4,110,199,1,199,'2022-11-12',14),
(81,1,103,899,1,899,'2022-11-12',14),
(81,2,103,899,2,1798,'2022-11-12',14),
(81,3,112,99,1,99,'2022-11-12',14),
(81,4,111,139,2,278,'2022-11-12',14),
(81,5,109,219,1,219,'2022-11-12',14);

/*Table structure for table `stresstest` */

DROP TABLE IF EXISTS `stresstest`;

CREATE TABLE `stresstest` (
  `StressTestID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NazivTesta` varchar(50) NOT NULL,
  `OpisTesta` varchar(255) NOT NULL,
  PRIMARY KEY (`StressTestID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `stresstest` */

insert  into `stresstest`(`StressTestID`,`NazivTesta`,`OpisTesta`) values 
(3,'Superposition','Test namenjen za testiranje grafickih kartica'),
(5,'Prime95','Test za cpu i ram koji se fokusira na integritet i performanse pokretnog zareza'),
(6,'CinebenchR20','Besplatan test za testiranje cpu i ram'),
(7,'Heaven','Besplatni test za testiranje graficke kartice');

/*Table structure for table `tipkomponente` */

DROP TABLE IF EXISTS `tipkomponente`;

CREATE TABLE `tipkomponente` (
  `TipKomponenteID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NazivTipa` varchar(50) NOT NULL,
  PRIMARY KEY (`TipKomponenteID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `tipkomponente` */

insert  into `tipkomponente`(`TipKomponenteID`,`NazivTipa`) values 
(1,'GPU'),
(2,'CPU'),
(3,'RAM'),
(4,'SSD'),
(5,'HDD');

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `profile_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_type` (`profile_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user_profile` */

insert  into `user_profile`(`id`,`profile_name`) values 
(2,'ADMIN'),
(1,'USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
