CREATE SCHEMA `recordsDB`;

use recordsDB;

CREATE TABLE `packet` (
  `device_id` bigint NOT NULL,
  `timestamp_start` bigint NOT NULL,
  `avg` double NOT NULL,
  `count` int NOT NULL,
  `max` double NOT NULL,
  `min` double NOT NULL,
  `sum` double NOT NULL,
  PRIMARY KEY (`device_id`,`timestamp_start`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci