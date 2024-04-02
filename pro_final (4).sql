-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2023 a las 13:41:47
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pro_final`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apartamento`
--

CREATE TABLE `apartamento` (
  `numero_unico` int(11) NOT NULL,
  `numero_apartamento` int(11) NOT NULL,
  `numero_bloque` int(11) NOT NULL,
  `matricula` int(11) DEFAULT NULL,
  `parqueadero` int(11) DEFAULT NULL,
  `asignado` varchar(1) NOT NULL,
  `disponible` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `apartamento`
--

INSERT INTO `apartamento` (`numero_unico`, `numero_apartamento`, `numero_bloque`, `matricula`, `parqueadero`, `asignado`, `disponible`) VALUES
(1, 101, 1, 1, 1, 'S', 'N'),
(2, 102, 1, 2, 2, 'S', 'N'),
(3, 201, 1, 3, 3, 'S', 'N'),
(4, 202, 1, NULL, NULL, 'S', 'N'),
(5, 101, 2, NULL, NULL, 'S', 'N'),
(6, 102, 2, NULL, NULL, 'S', 'N'),
(7, 201, 2, NULL, NULL, 'S', 'S'),
(8, 202, 2, NULL, NULL, 'S', 'S'),
(9, 101, 3, NULL, NULL, 'S', 'S'),
(10, 102, 3, NULL, NULL, 'S', 'S'),
(11, 201, 3, NULL, NULL, 'S', 'S'),
(12, 202, 3, NULL, NULL, 'S', 'S'),
(13, 101, 4, NULL, NULL, 'N', 'S'),
(14, 102, 4, NULL, NULL, 'N', 'S'),
(15, 201, 4, NULL, NULL, 'N', 'S'),
(16, 202, 4, NULL, NULL, 'N', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cobro`
--

CREATE TABLE `cobro` (
  `numero` int(11) NOT NULL,
  `mes` varchar(14) NOT NULL,
  `vigencia` int(11) NOT NULL,
  `fecha_generacion` date NOT NULL,
  `propietario_apartamento` int(11) NOT NULL,
  `residente_apartamento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `concepto`
--

CREATE TABLE `concepto` (
  `numero` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `valor` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item_cobro`
--

CREATE TABLE `item_cobro` (
  `cobro` int(11) NOT NULL,
  `concepto` int(11) NOT NULL,
  `valor_cobro` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `meses_generados`
--

CREATE TABLE `meses_generados` (
  `mes` varchar(14) NOT NULL,
  `vigencia` int(11) NOT NULL,
  `generado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `nombre1` varchar(20) NOT NULL,
  `nombre2` varchar(20) NOT NULL,
  `apellido1` varchar(20) NOT NULL,
  `apellido2` varchar(20) NOT NULL,
  `celular` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `tipo` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre1`, `nombre2`, `apellido1`, `apellido2`, `celular`, `email`, `tipo`) VALUES
(16, 'e', 'd', 'w', 'a', '16', 'r', 'Propietario/Residente'),
(21, 'y', 'y', 'y', 'y', '21', 'y', 'Residente'),
(76, 'p', 'p', 'p', 'p', '76', 'p', 'Residente'),
(80, 'x', 'x', 'x', 'x', '80', 'x', 'Propietario'),
(90, 'j', 'e', 'f', 'f', '90', 'q', 'Residente'),
(99, 'm', 'm', 'm', 'm', '99', 'm', 'Propietario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario_apartamento`
--

CREATE TABLE `propietario_apartamento` (
  `id` int(11) NOT NULL,
  `propietario` int(11) NOT NULL,
  `apartamento` int(11) NOT NULL,
  `fecha_compra` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `propietario_apartamento`
--

INSERT INTO `propietario_apartamento` (`id`, `propietario`, `apartamento`, `fecha_compra`) VALUES
(1, 16, 1, '2023-11-01'),
(2, 16, 2, '2023-11-26'),
(3, 16, 3, '2023-11-03'),
(4, 16, 5, '2023-12-02'),
(5, 16, 4, '2023-12-01'),
(6, 16, 6, '2023-12-05'),
(7, 99, 8, '2023-12-05'),
(8, 99, 7, '2023-12-06'),
(9, 99, 10, '2023-12-04'),
(12, 99, 11, '2023-12-01'),
(20, 80, 9, '2023-12-01'),
(21, 80, 12, '2023-12-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `residente_apartamento`
--

CREATE TABLE `residente_apartamento` (
  `id` int(11) NOT NULL,
  `residente` int(11) NOT NULL,
  `apartamento` int(11) NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `residente_apartamento`
--

INSERT INTO `residente_apartamento` (`id`, `residente`, `apartamento`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 21, 1, '2023-11-01', '2023-11-01'),
(2, 90, 2, '2023-11-01', NULL),
(3, 76, 3, '2023-11-01', '2023-11-22'),
(5, 90, 4, '2023-06-01', '2023-11-08'),
(6, 16, 5, '2023-11-08', '2023-11-09'),
(7, 16, 6, '2023-11-01', '2023-11-02');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_cuentas_cobro`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `v_cuentas_cobro` (
`nombre1` varchar(20)
,`nombre2` varchar(20)
,`numero_apartamento` int(11)
,`numero_bloque` int(11)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `v_cuentas_cobro`
--
DROP TABLE IF EXISTS `v_cuentas_cobro`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_cuentas_cobro`  AS SELECT `p`.`nombre1` AS `nombre1`, `p`.`nombre2` AS `nombre2`, `a`.`numero_apartamento` AS `numero_apartamento`, `a`.`numero_bloque` AS `numero_bloque` FROM ((`propietario_apartamento` `pa` join `persona` `p`) join `apartamento` `a`) WHERE `pa`.`propietario` = `p`.`id` AND `pa`.`apartamento` = `a`.`numero_unico` ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apartamento`
--
ALTER TABLE `apartamento`
  ADD PRIMARY KEY (`numero_unico`),
  ADD UNIQUE KEY `unq_apartamento_bloque` (`numero_apartamento`,`numero_bloque`),
  ADD UNIQUE KEY `unq_matricula` (`matricula`);

--
-- Indices de la tabla `cobro`
--
ALTER TABLE `cobro`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `fk_cobro_meses_generados` (`mes`,`vigencia`),
  ADD KEY `fk_cobro_propietario_apartamento` (`propietario_apartamento`),
  ADD KEY `fk_cobro_residente_apartamento` (`residente_apartamento`);

--
-- Indices de la tabla `concepto`
--
ALTER TABLE `concepto`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `item_cobro`
--
ALTER TABLE `item_cobro`
  ADD PRIMARY KEY (`cobro`,`concepto`),
  ADD KEY `fk_itemCobro_concepto` (`concepto`),
  ADD KEY `fk_itemCobro_cobro` (`cobro`);

--
-- Indices de la tabla `meses_generados`
--
ALTER TABLE `meses_generados`
  ADD PRIMARY KEY (`mes`,`vigencia`),
  ADD UNIQUE KEY `unq_meses_generados` (`mes`,`vigencia`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unq_celular_persona` (`celular`),
  ADD UNIQUE KEY `unq_email_persona` (`email`);

--
-- Indices de la tabla `propietario_apartamento`
--
ALTER TABLE `propietario_apartamento`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unq_propietario_apartamento` (`propietario`,`apartamento`),
  ADD KEY `fk_propietarioApartamento_apartamento` (`apartamento`);

--
-- Indices de la tabla `residente_apartamento`
--
ALTER TABLE `residente_apartamento`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unq_residente_apartamento` (`residente`,`apartamento`),
  ADD KEY `fk_residenteApartamento_apartamento` (`apartamento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apartamento`
--
ALTER TABLE `apartamento`
  MODIFY `numero_unico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `cobro`
--
ALTER TABLE `cobro`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `concepto`
--
ALTER TABLE `concepto`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `propietario_apartamento`
--
ALTER TABLE `propietario_apartamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `residente_apartamento`
--
ALTER TABLE `residente_apartamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cobro`
--
ALTER TABLE `cobro`
  ADD CONSTRAINT `fk_cobro_meses_generados` FOREIGN KEY (`mes`,`vigencia`) REFERENCES `meses_generados` (`mes`, `vigencia`),
  ADD CONSTRAINT `fk_cobro_propietario_apartamento` FOREIGN KEY (`propietario_apartamento`) REFERENCES `propietario_apartamento` (`id`),
  ADD CONSTRAINT `fk_cobro_residente_apartamento` FOREIGN KEY (`residente_apartamento`) REFERENCES `residente_apartamento` (`id`);

--
-- Filtros para la tabla `item_cobro`
--
ALTER TABLE `item_cobro`
  ADD CONSTRAINT `fk_itemCobro_cobro` FOREIGN KEY (`cobro`) REFERENCES `cobro` (`numero`),
  ADD CONSTRAINT `fk_itemCobro_concepto` FOREIGN KEY (`concepto`) REFERENCES `concepto` (`numero`);

--
-- Filtros para la tabla `propietario_apartamento`
--
ALTER TABLE `propietario_apartamento`
  ADD CONSTRAINT `fk_propietarioApartamento_apartamento` FOREIGN KEY (`apartamento`) REFERENCES `apartamento` (`numero_unico`),
  ADD CONSTRAINT `fk_propietarioApartamento_propietario` FOREIGN KEY (`propietario`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `residente_apartamento`
--
ALTER TABLE `residente_apartamento`
  ADD CONSTRAINT `fk_residenteApartamento_apartamento` FOREIGN KEY (`apartamento`) REFERENCES `apartamento` (`numero_unico`),
  ADD CONSTRAINT `fk_residenteApartamento_residente` FOREIGN KEY (`residente`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
