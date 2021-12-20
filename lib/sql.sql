CREATE DATABASE `test`;

-- test.`user` definition

CREATE TABLE `user` (
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `homeAddr` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `jobAddr` varchar(100) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL
);


INSERT INTO `user` (name,password,phone,homeAddr,jobAddr,account) VALUES
	 ('admin','admin',NULL,NULL,NULL,'admin');