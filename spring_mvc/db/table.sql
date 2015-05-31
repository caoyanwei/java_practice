CREATE TABLE `Student` (
  `Id` int(3) NOT NULL AUTO_INCREMENT,
  `Name` varchar(10) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;