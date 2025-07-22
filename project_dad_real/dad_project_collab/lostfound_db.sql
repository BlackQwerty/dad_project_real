-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 21, 2025 at 01:18 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lostfound_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `date_reported` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `reported_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `name`, `category`, `description`, `location`, `date_reported`, `image_url`, `type`, `status`, `reported_by`) VALUES
(7, 'ip13', 'Electronics', 'nn', 'Library', '2025-07-05', 'https://i.ibb.co/ZpqbkLP0/city.jpg', 'lost', 'Claimed', 1),
(8, 'nigga', 'Accessories', 'hhh', 'Library', '2025-07-11', 'https://i.ibb.co/ZpqbkLP0/city.jpg', 'found', 'Claimed', 1),
(9, 'maroq', 'Other', 'nigga', 'Cafeteria', '2025-07-17', 'https://i.ibb.co/67DGtw98/jonatan-pie-3l3-Rw-Qd-HRHg-unsplash.jpg', 'lost', 'Matched', 1),
(10, 'nigga', 'Electronics', 'ss', 'Library', '2025-07-15', 'https://i.ibb.co/Kxw2QgYt/prom.jpg', 'found', 'Matched', 1),
(11, 'nigga', 'Electronics', 'dd', 'Cafeteria', '2025-07-08', 'https://i.ibb.co/sxdDkgn/image.jpg', 'found', 'Claimed', 1),
(12, 'Black BabaYaga', 'Clothing', 'xx', 'Cafeteria', '2025-07-09', 'https://i.ibb.co/Df8Df7gq/Coding-Mode-On.jpg', 'lost', 'pending', 1),
(13, 'maroq', 'Other', 'nigga2345', 'Student Center', '2025-07-11', 'https://i.ibb.co/sxdDkgn/image.jpg', 'lost', 'pending', 1),
(14, 'Apple Pencil', 'Electronics', 'Hilang di Sasana Niaga', 'Other', '2025-07-17', 'https://i.ibb.co/7N10n2Fs/shopping.webp', 'lost', 'pending', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `email`) VALUES
(1, 'admin', 'admin123', 'admin', 'admin@example.com'),
(2, 'ahmad', '12345', 'user', 'ahmad@example.com'),
(3, 'abu', 'abu123', 'admin', 'admin@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_item_reported_by` (`reported_by`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `fk_item_reported_by` FOREIGN KEY (`reported_by`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
