-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 14-02-2022 a las 05:52:59
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoavicola`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja`
--

CREATE TABLE `caja` (
  `id_caja` int(15) NOT NULL,
  `ingresos` double DEFAULT NULL,
  `egresos` double DEFAULT NULL,
  `Ganancia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `caja`
--

INSERT INTO `caja` (`id_caja`, `ingresos`, `egresos`, `Ganancia`) VALUES
(1, 400.5, 92.94, 307.56),
(2, 400.5, 216.86, 183.64);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `pago_hora` double DEFAULT NULL,
  `seguro_social_empleado` double NOT NULL,
  `seguro_social_empleador` double NOT NULL,
  `hora_laborada` double NOT NULL,
  `fecha_contratacion` varchar(20) DEFAULT NULL,
  `fecha_salida` varchar(20) DEFAULT NULL,
  `rol` varchar(20) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `nombre`, `apellido`, `cedula`, `pago_hora`, `seguro_social_empleado`, `seguro_social_empleador`, `hora_laborada`, `fecha_contratacion`, `fecha_salida`, `rol`, `descripcion`) VALUES
(1, 'josue', 'abrigo', '15615', 0, 9.45, 11.5, 0, '', '', 'Ninguno', 'No asignado'),
(2, 'Luis', 'Jimenez', '115926203', 0, 9.45, 11.5, 0, '', '', 'Ninguno', 'No asignado'),
(3, 'Jhon', 'Coronel', '114795322', 0, 9.45, 11.5, 0, '', '', 'Ninguno', 'No asignado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(10) NOT NULL,
  `cliente` varchar(20) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `fecha_emision` date NOT NULL,
  `cantidad` varchar(10) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `precio_unitario` double NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id_factura`, `cliente`, `cedula`, `direccion`, `telefono`, `fecha_emision`, `cantidad`, `descripcion`, `precio_unitario`, `total`) VALUES
(1, 'Santiago', '1150691804', 'Catamayo', '0979629225', '2022-02-13', '10', 'pollo en pluma', 0.9, 175.5),
(2, 'Javier Sarango', '114789232', 'Catamayo', '0994109382', '2022-02-13', '20', 'pollo en pluma', 0.9, 225);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `galpones`
--

CREATE TABLE `galpones` (
  `id_galpon` int(50) NOT NULL,
  `pollos` varchar(100) NOT NULL,
  `raza` varchar(15) NOT NULL,
  `cant_Suministrada` int(15) NOT NULL,
  `Tipo_Balanceado` varchar(30) NOT NULL,
  `alimentacion` int(15) NOT NULL,
  `creacion` date DEFAULT NULL,
  `finaliza` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `galpones`
--

INSERT INTO `galpones` (`id_galpon`, `pollos`, `raza`, `cant_Suministrada`, `Tipo_Balanceado`, `alimentacion`, `creacion`, `finaliza`) VALUES
(1, '185', 'cubanos', 50, 'Crecimiento', 3, '2022-02-09', '2022-02-22'),
(2, '300', 'cubanos', 50, 'Crecimiento', 4, '2022-02-23', '2020-02-02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mortalidad`
--

CREATE TABLE `mortalidad` (
  `id_mortalidad` int(15) NOT NULL,
  `id_galpon` int(15) NOT NULL,
  `pollos_actuales` int(15) NOT NULL,
  `pollos_fallecidos` int(20) NOT NULL,
  `total` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mortalidad`
--

INSERT INTO `mortalidad` (`id_mortalidad`, `id_galpon`, `pollos_actuales`, `pollos_fallecidos`, `total`) VALUES
(1, 1, 200, 10, 190),
(2, 2, 350, 50, 300),
(3, 1, 190, 5, 185);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registroseccion`
--

CREATE TABLE `registroseccion` (
  `id` int(4) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `correo` varchar(50) NOT NULL,
  `rol` varchar(25) NOT NULL,
  `fecha` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `registroseccion`
--

INSERT INTO `registroseccion` (`id`, `nombre`, `apellido`, `correo`, `rol`, `fecha`) VALUES
(1, 'josue', 'abrigo', 'santia', 'Empleado', '2022/02/13 21:47:26'),
(2, 'josue', 'abrigo', 'santia', 'Empleado', '2022/02/13 21:47:26'),
(3, 'josue', 'abrigo', 'santia', 'Empleado', '2022/02/13 21:59:22'),
(4, 'josue', 'abrigo', 'santia', 'Empleado', '2022/02/13 21:59:22'),
(5, 'josue', 'abrigo', 'santia', 'Empleado', '2022/02/13 22:01:58'),
(6, 'josue', 'abrigo', 'santia', 'Empleado', '2022/02/13 22:01:58'),
(7, 'Luis', 'Jimenez', 'Luis123', 'Administrado', '2022/02/13 22:36:54'),
(8, 'Luis', 'Jimenez', 'Luis123', 'Administrado', '2022/02/13 22:36:54');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `celular` varchar(10) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `direccion` varchar(250) NOT NULL,
  `password` varchar(25) NOT NULL,
  `cargo` varchar(12) NOT NULL,
  `autorizacion` tinyint(1) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `cedula`, `celular`, `correo`, `direccion`, `password`, `cargo`, `autorizacion`, `descripcion`) VALUES
(1, 'josue', 'abrigo', '15615', '0979515', 'santia', 'Catamayo', '123', 'Gerente', 1, 'Accede a los galpones'),
(2, 'Luis', 'Jimenez', '115926203', '09785626', 'Luis123', 'Loja', '123', 'Empleado', 0, 'administrar '),
(5, 'Jhon', 'Coronel', '114795322', '0978562456', 'JhonC', 'San Pedro', '123', 'Empleado', 1, 'Acceder a vacunas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacunas`
--

CREATE TABLE `vacunas` (
  `id_vacuna` int(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `farmaco` varchar(20) NOT NULL,
  `justificacion` varchar(30) NOT NULL,
  `dosis` double NOT NULL,
  `primeraVacuna` varchar(30) NOT NULL,
  `segundaVacuna` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vacunas`
--

INSERT INTO `vacunas` (`id_vacuna`, `nombre`, `farmaco`, `justificacion`, `dosis`, `primeraVacuna`, `segundaVacuna`) VALUES
(1, 'marek', 'Fármaco', 'vacuna inicial primera semana', 2, '2022-02-09', '2023-02-01');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `caja`
--
ALTER TABLE `caja`
  ADD PRIMARY KEY (`id_caja`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`,`cedula`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`);

--
-- Indices de la tabla `galpones`
--
ALTER TABLE `galpones`
  ADD PRIMARY KEY (`id_galpon`);

--
-- Indices de la tabla `mortalidad`
--
ALTER TABLE `mortalidad`
  ADD PRIMARY KEY (`id_mortalidad`);

--
-- Indices de la tabla `registroseccion`
--
ALTER TABLE `registroseccion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `vacunas`
--
ALTER TABLE `vacunas`
  ADD PRIMARY KEY (`id_vacuna`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `caja`
--
ALTER TABLE `caja`
  MODIFY `id_caja` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `galpones`
--
ALTER TABLE `galpones`
  MODIFY `id_galpon` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `mortalidad`
--
ALTER TABLE `mortalidad`
  MODIFY `id_mortalidad` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `registroseccion`
--
ALTER TABLE `registroseccion`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `vacunas`
--
ALTER TABLE `vacunas`
  MODIFY `id_vacuna` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
