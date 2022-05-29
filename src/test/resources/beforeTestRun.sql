CREATE TABLE IF NOT EXISTS `user` (
 `id` INTEGER PRIMARY KEY,
 `name` varchar(255) DEFAULT NULL,
 `cpf` varchar(255) DEFAULT NULL,
 `birthday` date DEFAULT NULL
);

INSERT INTO `user` (`id`, `name`, `cpf`, `birthday`) VALUES
(1, 'Pedro', '672.321.030-18', '1990-02-01'),
(2, 'Rosa', '539.952.180-67', '1990-02-01'),
(3, 'Joao', '114.993.220-11', '1990-02-01'),
(4, 'Thamires', '409.936.760-65', '1990-02-01'),
(5, 'Maria', '277.879.110-85', '1990-02-01'),
(6, 'Rafael', '967.410.090-35', '1990-02-01');
