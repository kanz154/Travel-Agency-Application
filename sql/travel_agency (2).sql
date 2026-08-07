-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2026 at 06:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `travel_agency`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `packageId` int(11) DEFAULT NULL,
  `bookingDate` varchar(50) DEFAULT NULL,
  `totalAmount` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `details` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingId`, `userId`, `packageId`, `bookingDate`, `totalAmount`, `status`, `details`) VALUES
(1, 1, 1, '2026-06-22', 15000, 'Confirmed', NULL),
(2, 1, 1, '2026-06-22', 15000, 'Confirmed', NULL),
(3, 1, 1, '2026-06-22', 15000, 'Confirmed', NULL),
(10, 17, 0, '2026-07-13T15:37:41.986', 0, 'Confirmed', 'Lahore → Islamabad — PKR 1,200 | Name: raha | Email: ra@gmail.com | Phone: 0333859817 | Date: 2026-07-25 | Persons: 2 Persons'),
(11, 18, -1, '2026-07-13 23:03:55', 28000, 'Confirmed', 'Pearl Continental, Lahore — PKR 28,000 / night | Name: Fatima | Email: wer@gmail.com | Phone: 0383328283 | Date: 2026-07-17 | Persons: 2 Persons'),
(12, 18, -1, '2026-07-13 23:10:01', 6450000, 'Confirmed', 'Maldives 6 Nights — PKR 450,000 / person | Name: Fatima | Email: wer@gmail.com | Phone: 0383328283 | Date: 2026-07-18 | Persons: 3 Persons'),
(13, 19, 4, '2026-07-29', 35000, 'Confirmed', 'Serena Hotel, Islamabad — PKR 35,000 / night | Name: KANZAA | Email: qae@gmail.com | Phone: 0357239877 | Date: 2026-07-29 | Persons: 4 Persons'),
(14, 20, 4, '2026-07-25', 1200, 'Confirmed', 'Lahore → Islamabad — PKR 1,200 | Name: Rameen | Email: def@gmail.com | Phone: 0356758912 | Date: 2026-07-25 | Persons: 5+ Persons'),
(15, 21, 4, '2026-07-15', 98000, 'Confirmed', 'Islamabad → Istanbul — PKR 98,000 | Name: ALEENA | Email: al@gmail.com | Phone: 043489231 | Date: 2026-07-15 | Persons: 2 Persons'),
(16, 22, -1, '2026-07-15', 5120000, 'Confirmed', 'Bangkok 5 Nights — PKR 120,000 / person | Name: Laiba | Email: laiba@gmail.com | Phone: 0347337 | Date: 2026-07-15 | Persons: 2 Persons'),
(17, 23, 5, '2026-07-02', 28000, 'Confirmed', 'Pearl Continental, Lahore — PKR 28,000 / night | Name: Ali | Email: ali09@gmail.com | Phone: 0378979897 | Date: 2026-07-02 | Persons: 2 Persons'),
(18, 24, 5, '2026-07-02', 900, 'Confirmed', 'Islamabad → Peshawar — PKR 900 | Name: Fatima | Email: fatima87@gmail.com | Phone: 38982973993 | Date: 2026-07-02 | Persons: 4 Persons'),
(19, 25, 6, '2026-07-03', 28000, 'Confirmed', 'Pearl Continental, Lahore — PKR 28,000 / night | Name: Ali | Email: ali89@gmail.com | Phone: 03775677748 | Date: 2026-07-03 | Persons: 2 Persons'),
(20, 26, 7, '2026-07-18', 280000, 'Confirmed', 'Burj Al Arab, Dubai — PKR 280,000 / night | Name: SIDRA | Email: qwe@gmail.com | Phone: 036273677 | Date: 2026-07-18 | Persons: 2 Persons'),
(21, 27, 5, '2026-07-08', 98000, 'Confirmed', 'Islamabad → Istanbul — PKR 98,000 | Name: Kanza | Email: kanza67@gmal.com | Phone: 036688578 | Date: 2026-07-08 | Persons: 4 Persons'),
(22, 28, 6, '2026-07-15', 185000, 'Confirmed', 'Lahore → London — PKR 185,000 | Name: MINAHIL | Email: min@gmail.com | Phone: 03848721 | Date: 2026-07-15 | Persons: 3 Persons');

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `busId` int(11) NOT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `departureTime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`busId`, `origin`, `destination`, `price`, `departureTime`) VALUES
(1, 'Islamabad', 'Peshawar', 1500, '2026-07-02 08:00 AM'),
(4, 'Lahore', 'Islamabad', 1200, '2026-07-25'),
(5, 'Islamabad', 'Peshawar', 900, '2026-07-02');

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `flightId` int(11) NOT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `departureTime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`flightId`, `origin`, `destination`, `price`, `departureTime`) VALUES
(1, 'Karachi', 'Lahore', 8000, '2026-07-01 10:00 AM'),
(2, 'Karachi', 'Lahore', 8000, '2026-07-01 10:00 AM'),
(3, 'Karachi', 'Lahore', 8000, '2026-07-01 10:00 AM'),
(4, 'Islamabad', 'Istanbul', 98000, '2026-07-15'),
(5, 'Islamabad', 'Istanbul', 98000, '2026-07-08'),
(6, 'Lahore', 'London', 185000, '2026-07-15');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `hotelId` int(11) NOT NULL,
  `hotelName` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `pricePerNight` int(11) DEFAULT NULL,
  `details` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`hotelId`, `hotelName`, `location`, `pricePerNight`, `details`) VALUES
(1, 'Grand Hotel', 'Goa', 3000, 'AC Room with Sea View'),
(4, 'Serena Hotel, Islamabad', 'Serena Hotel, Islamabad', 35000, 'Serena Hotel, Islamabad — PKR 35,000 / night | Name: KANZAA | Email: qae@gmail.com | Phone: 0357239877 | Date: 2026-07-29 | Persons: 4 Persons'),
(5, 'Pearl Continental, Lahore', 'Pearl Continental, Lahore', 28000, 'Pearl Continental, Lahore — PKR 28,000 / night | Name: Ali | Email: ali09@gmail.com | Phone: 0378979897 | Date: 2026-07-02 | Persons: 2 Persons'),
(6, 'Pearl Continental, Lahore', 'Pearl Continental, Lahore', 28000, 'Pearl Continental, Lahore — PKR 28,000 / night | Name: Ali | Email: ali89@gmail.com | Phone: 03775677748 | Date: 2026-07-03 | Persons: 2 Persons'),
(7, 'Burj Al Arab, Dubai', 'Burj Al Arab, Dubai', 280000, 'Burj Al Arab, Dubai — PKR 280,000 / night | Name: SIDRA | Email: qwe@gmail.com | Phone: 036273677 | Date: 2026-07-18 | Persons: 2 Persons');

-- --------------------------------------------------------

--
-- Table structure for table `package`
--

CREATE TABLE `package` (
  `packageId` int(11) NOT NULL,
  `packageName` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `duration` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `package`
--

INSERT INTO `package` (`packageId`, `packageName`, `destination`, `price`, `duration`) VALUES
(1, 'Goa Beach Trip', 'Goa', 15000, '5 Days'),
(2, 'Goa Beach Trip', 'Goa', 15000, '5 Days'),
(3, 'Goa Beach Trip', 'Goa', 15000, '5 Days');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `userId` int(11) NOT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`userId`, `fullName`, `email`, `phoneNumber`, `password`) VALUES
(1, 'Ali Khan', 'ali@test.com', '03001234567', 'password123'),
(3, 'Kanza', 'abc@gmail.com', '23465768', '123'),
(4, 'Fatima', 'fati@gmail.com', '923455076', '456'),
(5, 'Safa', 'safa@gmail.com', '92313022930', '12345'),
(6, 'fatima', 'fatima12@gmail.com', '0387676877', 'fati'),
(7, 'Munazza', 'munazza2@gmail.com', '03274274782', '12345'),
(9, 'Huzaifa', 'huzaifa2@gmail.com', '03784676473', '12345'),
(10, 'Syeda Kanza', 'kan@gmail.com', '023812675', '890'),
(14, 'kanza', 'kan@gmail.com', '03481929292', '345'),
(15, 'Munazza', 'mun@gmail.com', '03782957584', '345'),
(21, 'ALEENA', 'al@gmail.com', '043489231', '123'),
(22, 'Laiba', 'laiba@gmail.com', '0347337', '456'),
(23, 'Ali', 'ali09@gmail.com', '0378979897', 'ali110'),
(24, 'Fatima', 'fatima87@gmail.com', '38982973993', '123'),
(25, 'Ali', 'ali89@gmail.com', '03775677748', '789'),
(26, 'SIDRA', 'qwe@gmail.com', '036273677', '12345'),
(27, 'Kanza', 'kanza67@gmal.com', '036688578', '110'),
(28, 'MINAHIL', 'min@gmail.com', '03848721', '567');

-- --------------------------------------------------------

--
-- Table structure for table `visa`
--

CREATE TABLE `visa` (
  `visaId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `visaType` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `details` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `visa`
--

INSERT INTO `visa` (`visaId`, `userId`, `country`, `visaType`, `status`, `details`) VALUES
(1, 1, 'Dubai', 'Tourist', 'Pending', 'Required documents submitted'),
(2, 1, 'Dubai', 'Tourist', 'Pending', 'Required documents submitted'),
(3, 1, 'Dubai', 'Tourist', 'Pending', 'Required documents submitted');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingId`);

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`busId`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flightId`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotelId`);

--
-- Indexes for table `package`
--
ALTER TABLE `package`
  ADD PRIMARY KEY (`packageId`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `visa`
--
ALTER TABLE `visa`
  ADD PRIMARY KEY (`visaId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `bus`
--
ALTER TABLE `bus`
  MODIFY `busId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `flight`
--
ALTER TABLE `flight`
  MODIFY `flightId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `hotelId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `package`
--
ALTER TABLE `package`
  MODIFY `packageId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `visa`
--
ALTER TABLE `visa`
  MODIFY `visaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
