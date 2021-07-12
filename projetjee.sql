-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2021 at 02:50 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projetjee`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `ref` varchar(50) DEFAULT NULL,
  `libelle` varchar(200) NOT NULL,
  `prix` double NOT NULL,
  `date_exp` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `ref`, `libelle`, `prix`, `date_exp`) VALUES
(1, '#123DE', 'produit 1', 2800, '2022-12-15'),
(4, '#123DE13', 'produit 2', 5000, '2022-10-25'),
(5, '#jkl', 'produit 5', 3000, NULL),
(6, '#123DE1389', 'produit 5', 2000, '2022-10-12'),
(7, '#hel', 'product 7', 4500, NULL),
(75, 'Aut amet occaecat e', 'Natus vero dignissim', 80, '2005-11-21'),
(87, 'Quod nulla eaque qui', 'Dolor qui cupidatat ', 71, '1988-12-27'),
(88, 'Elit omnis nulla te', 'Dolor corrupti temp', 74, '2006-01-19');

-- --------------------------------------------------------

--
-- Table structure for table `operation`
--

CREATE TABLE `operation` (
  `id` int(11) NOT NULL,
  `montant` double NOT NULL,
  `type` int(11) NOT NULL DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `operation`
--

INSERT INTO `operation` (`id`, `montant`, `type`, `created_at`) VALUES
(32, 5000, 1, '2021-07-02 23:15:41'),
(34, 3000, 1, '2021-07-02 23:19:58'),
(35, 3000, 1, '2021-07-02 23:20:17'),
(36, 6000, 2, '2021-07-02 23:35:46'),
(37, 6000, 2, '2021-07-04 13:38:26'),
(38, 7000, 1, '2021-07-04 13:40:33');

-- --------------------------------------------------------

--
-- Table structure for table `operation_item`
--

CREATE TABLE `operation_item` (
  `id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `operation_id` int(11) DEFAULT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `operation_item`
--

INSERT INTO `operation_item` (`id`, `article_id`, `operation_id`, `quantite`) VALUES
(14, 5, 32, 1),
(16, 7, 34, 2),
(17, 7, 35, 2),
(18, 7, 36, 2),
(19, 7, 37, 2),
(20, 8, 38, 200);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `article_id`, `quantite`) VALUES
(1, 4, 6),
(2, 5, 2),
(5, 7, 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `full_name` varchar(200) NOT NULL,
  `login` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `full_name`, `login`, `email`, `password`, `token`) VALUES
(7, 'test1', 'user2', 'test@test.mr', '$2a$06$PvE.EyYH9NdrNS72XiT3eundysW30Kzni0DvEUyCpeSYAnHZ4hTMi', NULL),
(8, 'test ty', 'user3', 'test@test.mr', '$2a$06$ZqrTmlrDeEldUe7Te9AfjeKBRNeYGSIe5/6SEpBOEz42a/u46HXHS', NULL),
(10, 'Irene Olsen', 'Sint culpa iusto es', 'bepi@mailinator.com', '$2a$06$nhU9ulK/EJrbtGEYDqLFb.vyv7gId55pwNGIC8foI/yDwshuQqVnu', NULL),
(11, 'Callie Talley', 'Quo quae voluptatibu', 'kyjux@mailinator.com', '$2a$06$bBGEacJLVlUNtky/giuT2.UIkOUf8JRiwB2/ZjE6xcI7jsPWRcZcu', NULL),
(12, 'Urielle Ortega', 'Perspiciatis accusa', 'fodax@mailinator.com', '$2a$06$w4wc679W5OeKIxFVxtIWeu0sfrHKtupvMy7OuwdgtrJoTx7csXZce', NULL),
(21, 'Dieter 21', 'dete login', 'dete21@gmail.com', '$2a$06$ZXU18WixS8FUWSVuFooXw.SmzE2S6jJsRrTXaT/JhzQJKQYo4yxy6', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ref` (`ref`);

--
-- Indexes for table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `operation_item`
--
ALTER TABLE `operation_item`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `article_id` (`article_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- AUTO_INCREMENT for table `operation`
--
ALTER TABLE `operation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `operation_item`
--
ALTER TABLE `operation_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
