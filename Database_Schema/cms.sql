-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2024 at 08:20 PM
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
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `courseID` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `program` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `lecturerID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseID`, `name`, `program`, `room`, `lecturerID`) VALUES
(1, 'Intro to CS', 'Computer Science', '101', 1),
(2, 'Data Fundamentals', 'Data Science', '102', 2),
(3, 'Software Dev', 'Software Engineering', '103', 3),
(4, 'IT Basics', 'Information Technology', '104', 4),
(5, 'Cybersecurity 101', 'Cybersecurity', '105', 5);

-- --------------------------------------------------------

--
-- Table structure for table `enrollments`
--

CREATE TABLE `enrollments` (
  `enrollmentID` int(11) NOT NULL,
  `studentID` int(11) DEFAULT NULL,
  `courseID` int(11) DEFAULT NULL,
  `grade` char(2) DEFAULT NULL,
  `status` enum('completed','repeating') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `enrollments`
--

INSERT INTO `enrollments` (`enrollmentID`, `studentID`, `courseID`, `grade`, `status`) VALUES
(1, 1, 1, 'A', 'completed'),
(2, 2, 2, 'B', 'completed'),
(3, 3, 3, 'A', 'completed'),
(4, 4, 4, 'B', 'completed'),
(5, 5, 5, 'A', 'completed'),
(6, 6, 1, 'B', 'completed'),
(7, 7, 2, 'A', 'completed'),
(8, 8, 3, 'B', 'completed'),
(9, 9, 4, 'A', 'completed'),
(10, 10, 5, 'B', 'completed'),
(11, 11, 1, 'A', 'completed'),
(12, 12, 2, 'B', 'completed'),
(13, 13, 3, 'A', 'completed'),
(14, 14, 4, 'B', 'completed'),
(15, 15, 5, 'A', 'completed'),
(16, 16, 1, 'B', 'completed'),
(17, 17, 2, 'A', 'completed'),
(18, 18, 3, 'B', 'completed'),
(19, 19, 4, 'A', 'completed'),
(20, 20, 5, 'B', 'completed'),
(21, 21, 1, 'A', 'repeating'),
(22, 22, 2, 'B', 'completed'),
(23, 23, 3, 'A', 'repeating'),
(24, 24, 4, 'B', 'completed'),
(25, 25, 5, 'A', 'repeating'),
(26, 26, 1, 'B', 'completed'),
(27, 27, 2, 'A', 'repeating'),
(28, 28, 3, 'B', 'completed'),
(29, 29, 4, 'A', 'repeating'),
(30, 30, 5, 'B', 'completed'),
(31, 31, 1, 'A', 'repeating'),
(32, 32, 2, 'B', 'completed'),
(33, 33, 3, 'A', 'repeating'),
(34, 34, 4, 'B', 'completed'),
(35, 35, 5, 'A', 'repeating'),
(36, 36, 1, 'B', 'completed'),
(37, 37, 2, 'A', 'repeating'),
(38, 38, 3, 'B', 'completed'),
(39, 39, 4, 'A', 'repeating'),
(40, 40, 5, 'B', 'completed'),
(41, 41, 1, 'A', 'repeating'),
(42, 42, 2, 'B', 'completed'),
(43, 43, 3, 'A', 'repeating'),
(44, 44, 4, 'B', 'completed'),
(45, 45, 5, 'A', 'repeating'),
(46, 46, 1, 'B', 'completed'),
(47, 47, 2, 'A', 'repeating'),
(48, 48, 3, 'B', 'completed'),
(49, 49, 4, 'A', 'repeating'),
(50, 50, 5, 'B', 'completed'),
(51, 51, 1, 'A', 'repeating'),
(52, 52, 2, 'B', 'completed'),
(53, 53, 3, 'A', 'repeating'),
(54, 54, 4, 'B', 'completed'),
(55, 55, 5, 'A', 'repeating'),
(56, 56, 1, 'B', 'completed'),
(57, 57, 2, 'A', 'repeating'),
(58, 58, 3, 'B', 'completed'),
(59, 59, 4, 'A', 'repeating'),
(60, 60, 5, 'B', 'completed'),
(61, 61, 1, 'A', 'repeating'),
(62, 62, 2, 'B', 'completed'),
(63, 63, 3, 'A', 'repeating'),
(64, 64, 4, 'B', 'completed'),
(65, 65, 5, 'A', 'repeating'),
(66, 66, 1, 'B', 'completed'),
(67, 67, 2, 'A', 'repeating'),
(68, 68, 3, 'B', 'completed'),
(69, 69, 4, 'A', 'repeating'),
(70, 70, 5, 'B', 'completed'),
(71, 71, 1, 'A', 'repeating'),
(72, 72, 2, 'B', 'completed'),
(73, 73, 3, 'A', 'repeating'),
(74, 74, 4, 'B', 'completed'),
(75, 75, 5, 'A', 'repeating'),
(76, 76, 1, 'B', 'completed'),
(77, 77, 2, 'A', 'repeating'),
(78, 78, 3, 'B', 'completed'),
(79, 79, 4, 'A', 'repeating'),
(80, 80, 5, 'B', 'completed'),
(81, 81, 2, 'A', 'repeating'),
(82, 82, 2, 'B', 'completed'),
(83, 83, 3, 'A', 'repeating'),
(84, 84, 2, 'B', 'completed'),
(85, 85, 1, 'A', 'repeating'),
(86, 86, 1, 'B', 'completed'),
(87, 87, 1, 'A', 'repeating'),
(88, 88, 1, 'B', 'completed'),
(89, 89, 1, 'A', 'repeating'),
(90, 90, 5, 'B', 'completed'),
(91, 91, 3, 'A', 'repeating'),
(92, 92, 2, 'B', 'completed'),
(93, 93, 1, 'A', 'repeating'),
(94, 94, 4, 'B', 'completed'),
(95, 95, 5, 'A', 'repeating'),
(96, 96, 4, 'B', 'completed'),
(97, 97, 1, 'A', 'repeating'),
(98, 98, 5, 'B', 'completed'),
(99, 99, 4, 'A', 'repeating'),
(100, 100, 5, 'B', 'completed');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedbackID` int(11) NOT NULL,
  `courseID` int(11) DEFAULT NULL,
  `studentID` int(11) DEFAULT NULL,
  `comment` text DEFAULT NULL,
  `rating` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedbackID`, `courseID`, `studentID`, `comment`, `rating`) VALUES
(1, 1, 1, 'Great course!', 5),
(2, 2, 2, 'Very informative', 4),
(3, 3, 3, 'Challenging but rewarding', 5),
(4, 4, 4, 'Needed more practical sessions', 3),
(5, 5, 5, 'Excellent material', 5),
(6, 1, 6, 'Too difficult', 2),
(7, 2, 7, 'Well-structured course', 5),
(8, 3, 8, 'Could use more examples', 3),
(9, 4, 9, 'Engaging lectures', 5),
(10, 5, 10, 'Great intro to the topic', 4);

-- --------------------------------------------------------

--
-- Table structure for table `lecturers`
--

CREATE TABLE `lecturers` (
  `lecturerID` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` enum('associate lecturer','senior lecturer','professor','program manager') DEFAULT NULL,
  `expertise` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lecturers`
--

INSERT INTO `lecturers` (`lecturerID`, `name`, `role`, `expertise`) VALUES
(1, 'LecturerA', 'senior lecturer', 'Computer Science'),
(2, 'LecturerB', 'professor', 'Data Science'),
(3, 'LecturerC', 'associate lecturer', 'Software Engineering'),
(4, 'LecturerD', 'program manager', 'Information Technology'),
(5, 'LecturerE', 'senior lecturer', 'Cybersecurity'),
(6, 'LecturerF', 'senior lecturer', 'Artificial Intelligence'),
(7, 'LecturerG', 'associate lecturer', 'Machine Learning'),
(8, 'LecturerH', 'professor', 'Database Systems'),
(9, 'LecturerI', 'program manager', 'Human-Computer Interaction'),
(10, 'LecturerJ', 'senior lecturer', 'Network Security');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `studentID` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `program` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`studentID`, `name`, `program`) VALUES
(1, 'Student One', 'Computer Science'),
(2, 'Student Two', 'Data Science'),
(3, 'Student Three', 'Software Engineering'),
(4, 'Student Four', 'Information Technology'),
(5, 'Student Five', 'Cybersecurity'),
(6, 'Student Six', 'Artificial Intelligence'),
(7, 'Student Seven', 'Machine Learning'),
(8, 'Student Eight', 'Database Systems'),
(9, 'Student Nine', 'Human-Computer Interaction'),
(10, 'Student Ten', 'Network Security'),
(11, 'Student Eleven', 'Computer Science'),
(12, 'Student Twelve', 'Data Science'),
(13, 'Student Thirteen', 'Software Engineering'),
(14, 'Student Fourteen', 'Information Technology'),
(15, 'Student Fifteen', 'Cybersecurity'),
(16, 'Student Sixteen', 'Artificial Intelligence'),
(17, 'Student Seventeen', 'Machine Learning'),
(18, 'Student Eighteen', 'Database Systems'),
(19, 'Student Nineteen', 'Human-Computer Interaction'),
(20, 'Student Twenty', 'Network Security'),
(21, 'Student Twenty-One', 'Computer Science'),
(22, 'Student Twenty-Two', 'Data Science'),
(23, 'Student Twenty-Three', 'Software Engineering'),
(24, 'Student Twenty-Four', 'Information Technology'),
(25, 'Student Twenty-Five', 'Cybersecurity'),
(26, 'Student Twenty-Six', 'Artificial Intelligence'),
(27, 'Student Twenty-Seven', 'Machine Learning'),
(28, 'Student Twenty-Eight', 'Database Systems'),
(29, 'Student Twenty-Nine', 'Human-Computer Interaction'),
(30, 'Student Thirty', 'Network Security'),
(31, 'Student Thirty-One', 'Computer Science'),
(32, 'Student Thirty-Two', 'Data Science'),
(33, 'Student Thirty-Three', 'Software Engineering'),
(34, 'Student Thirty-Four', 'Information Technology'),
(35, 'Student Thirty-Five', 'Cybersecurity'),
(36, 'Student Thirty-Six', 'Artificial Intelligence'),
(37, 'Student Thirty-Seven', 'Machine Learning'),
(38, 'Student Thirty-Eight', 'Database Systems'),
(39, 'Student Thirty-Nine', 'Human-Computer Interaction'),
(40, 'Student Forty', 'Network Security'),
(41, 'Student Forty-One', 'Computer Science'),
(42, 'Student Forty-Two', 'Data Science'),
(43, 'Student Forty-Three', 'Software Engineering'),
(44, 'Student Forty-Four', 'Information Technology'),
(45, 'Student Forty-Five', 'Cybersecurity'),
(46, 'Student Forty-Six', 'Artificial Intelligence'),
(47, 'Student Forty-Seven', 'Machine Learning'),
(48, 'Student Forty-Eight', 'Database Systems'),
(49, 'Student Forty-Nine', 'Human-Computer Interaction'),
(50, 'Student Fifty', 'Network Security'),
(51, 'Student Fifty-One', 'Computer Science'),
(52, 'Student Fifty-Two', 'Data Science'),
(53, 'Student Fifty-Three', 'Software Engineering'),
(54, 'Student Fifty-Four', 'Information Technology'),
(55, 'Student Fifty-Five', 'Cybersecurity'),
(56, 'Student Fifty-Six', 'Artificial Intelligence'),
(57, 'Student Fifty-Seven', 'Machine Learning'),
(58, 'Student Fifty-Eight', 'Database Systems'),
(59, 'Student Fifty-Nine', 'Human-Computer Interaction'),
(60, 'Student Sixty', 'Network Security'),
(61, 'Student Sixty-One', 'Computer Science'),
(62, 'Student Sixty-Two', 'Data Science'),
(63, 'Student Sixty-Three', 'Software Engineering'),
(64, 'Student Sixty-Four', 'Information Technology'),
(65, 'Student Sixty-Five', 'Cybersecurity'),
(66, 'Student Sixty-Six', 'Artificial Intelligence'),
(67, 'Student Sixty-Seven', 'Machine Learning'),
(68, 'Student Sixty-Eight', 'Database Systems'),
(69, 'Student Sixty-Nine', 'Human-Computer Interaction'),
(70, 'Student Seventy', 'Network Security'),
(71, 'Student Seventy-One', 'Computer Science'),
(72, 'Student Seventy-Two', 'Data Science'),
(73, 'Student Seventy-Three', 'Software Engineering'),
(74, 'Student Seventy-Four', 'Information Technology'),
(75, 'Student Seventy-Five', 'Cybersecurity'),
(76, 'Student Seventy-Six', 'Artificial Intelligence'),
(77, 'Student Seventy-Seven', 'Machine Learning'),
(78, 'Student Seventy-Eight', 'Database Systems'),
(79, 'Student Seventy-Nine', 'Human-Computer Interaction'),
(80, 'Student Eighty', 'Network Security'),
(81, 'Student Eighty-One', 'Computer Science'),
(82, 'Student Eighty-Two', 'Data Science'),
(83, 'Student Eighty-Three', 'Software Engineering'),
(84, 'Student Eighty-Four', 'Information Technology'),
(85, 'Student Eighty-Five', 'Cybersecurity'),
(86, 'Student Eighty-Six', 'Artificial Intelligence'),
(87, 'Student Eighty-Seven', 'Machine Learning'),
(88, 'Student Eighty-Eight', 'Database Systems'),
(89, 'Student Eighty-Nine', 'Human-Computer Interaction'),
(90, 'Student Ninety', 'Network Security'),
(91, 'Student Ninety-One', 'Computer Science'),
(92, 'Student Ninety-Two', 'Data Science'),
(93, 'Student Ninety-Three', 'Software Engineering'),
(94, 'Student Ninety-Four', 'Information Technology'),
(95, 'Student Ninety-Five', 'Cybersecurity'),
(96, 'Student Ninety-Six', 'Artificial Intelligence'),
(97, 'Student Ninety-Seven', 'Machine Learning'),
(98, 'Student Ninety-Eight', 'Database Systems'),
(99, 'Student Ninety-Nine', 'Human-Computer Interaction'),
(100, 'Student One Hundred', 'Network Security');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('Admin','Office','Lecturer') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `password`, `role`) VALUES
(1, 'LecturerA', 'pass1234', 'Lecturer'),
(2, 'LecturerB', 'pass1234', 'Lecturer'),
(3, 'LecturerC', 'pass1234', 'Lecturer'),
(4, 'LecturerD', 'pass1234', 'Lecturer'),
(5, 'LecturerE', 'pass1234', 'Lecturer'),
(6, 'LecturerF', 'pass1234', 'Lecturer'),
(7, 'LecturerG', 'pass1234', 'Lecturer'),
(8, 'LecturerH', 'pass1234', 'Lecturer'),
(9, 'LecturerI', 'pass1234', 'Lecturer'),
(10, 'LecturerJ', 'pass1234', 'Lecturer'),
(11, 'Admin', 'java', 'Admin'),
(12, 'Admin2', 'cpp', 'Admin'),
(13, 'Admin3', 'c', 'Admin'),
(14, 'Office1', 'officePass1', 'Office'),
(15, 'Office2', 'officePass2', 'Office'),
(16, 'Office3', 'officePass3', 'Office');

--
-- Triggers `users`
--
DELIMITER $$
CREATE TRIGGER `InsertIntoLecturers` AFTER INSERT ON `users` FOR EACH ROW BEGIN
    IF NEW.role = 'Lecturer' THEN
        INSERT INTO Lecturers (name, role, expertise) VALUES (NEW.username, '', '');
    END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`courseID`),
  ADD KEY `lecturerID` (`lecturerID`);

--
-- Indexes for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD PRIMARY KEY (`enrollmentID`),
  ADD KEY `studentID` (`studentID`),
  ADD KEY `courseID` (`courseID`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackID`),
  ADD KEY `courseID` (`courseID`),
  ADD KEY `studentID` (`studentID`);

--
-- Indexes for table `lecturers`
--
ALTER TABLE `lecturers`
  ADD PRIMARY KEY (`lecturerID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`studentID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `courseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `enrollments`
--
ALTER TABLE `enrollments`
  MODIFY `enrollmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedbackID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `lecturers`
--
ALTER TABLE `lecturers`
  MODIFY `lecturerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`lecturerID`) REFERENCES `lecturers` (`lecturerID`);

--
-- Constraints for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD CONSTRAINT `enrollments_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `students` (`studentID`),
  ADD CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `students` (`studentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
