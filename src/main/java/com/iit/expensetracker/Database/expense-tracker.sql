-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 16, 2019 at 11:56 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `expense-tracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryId` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `exp_limit` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryId`, `userId`, `category`, `type`, `exp_limit`) VALUES
('ccc21041-63ea-4fef-9023-27e91d300cb4', '7fa65ff0-4a3e-4cc5-b975-fae5c16b385e', 'drink', 'expense', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionId` varchar(255) NOT NULL,
  `categoryId` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transactionId`, `categoryId`, `amount`, `remarks`, `timestamp`, `userId`) VALUES
('0f47de02-c9f8-4539-92c2-70d151bb43f6', '12', 900, 'For the drink last night!', '2019-11-16 07:43:39', ''),
('12', '13', 34, '434', '2019-11-06 21:30:00', ''),
('13', '13', 34, '434', '2019-11-06 21:30:00', ''),
('803e2d03-c8ed-4b82-9459-435153c945e7', '12', 900, 'For the drink last night!', '2019-11-16 08:20:09', '7fa65ff0-4a3e-4cc5-b975-fae5c16b385e'),
('e14c8ae6-916a-4a69-999d-8af2437ebae8', '12', 900, 'For the drink last night!', '2019-11-16 10:46:53', '7fa65ff0-4a3e-4cc5-b975-fae5c16b385e');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `firstName`, `lastName`, `email`, `city`) VALUES
('7fa65ff0-4a3e-4cc5-b975-fae5c16b385e', 'Naveen', 'Perera', 'naveenperera777@gmail.com', 'colombo'),
('a8344dbb-fd0e-41cc-8e00-7610494e1121', 'Naveen', 'Perera', 'naveenperera777@gmail.com', 'colombo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryId`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
