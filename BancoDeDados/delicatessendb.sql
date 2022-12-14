-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 14-Dez-2022 às 05:22
-- Versão do servidor: 10.4.25-MariaDB
-- versão do PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `delicatessendb`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `rua` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `Data_nascimento` varchar(45) DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `telefone`, `nome`, `rua`, `bairro`, `cidade`, `Data_nascimento`, `cpf`) VALUES
(20, '(00) 0-0000-0000', 'William', 'Zona Rural', 'Figueiras', 'Passira ', '00/00/0000', '0000000000'),
(21, '(00) 0-0000-0000', 'Cintia', 'Rua da lama', 'Centro', 'Passira ', '00/00/0000', '0000000000');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

CREATE TABLE `estoque` (
  `id` int(11) NOT NULL,
  `item` varchar(45) DEFAULT NULL,
  `quantidade` varchar(45) DEFAULT NULL,
  `validade` varchar(45) DEFAULT NULL,
  `valor_compra` varchar(45) DEFAULT NULL,
  `venda_valor` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `estoque`
--

INSERT INTO `estoque` (`id`, `item`, `quantidade`, `validade`, `valor_compra`, `venda_valor`) VALUES
(4, 'copos /descartaveis', '500', '25/07/2023', '', ''),
(7, 'Pão', '100', '20 Dias', '', ''),
(8, 'Mortadela', '203', '09/08/2099', '', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

CREATE TABLE `funcionarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `data_nascimento` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `turno` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`id`, `nome`, `endereco`, `telefone`, `bairro`, `cidade`, `data_nascimento`, `cargo`, `turno`) VALUES
(7, 'William', 'Sitio figuieira', '(00) 0-00000-0000', 'zona rural', 'passira', '00/00/0000', 'Auxiliar', 'Noite'),
(10, 'Camile ', 'alto caturite', '(00) 0-0000-0000', 'arredores', 'Caruaru', '10/03/2011', 'Divulgação ', 'Flexivel');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itens`
--

CREATE TABLE `itens` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `validade` varchar(45) DEFAULT NULL,
  `valor` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `itens`
--

INSERT INTO `itens` (`id`, `nome`, `validade`, `valor`) VALUES
(4, 'Agua', '20/10/2025', '1,50'),
(6, 'Coca-cola', '14/12/2022', '7,00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `item` varchar(45) DEFAULT NULL,
  `data_entrega` varchar(45) DEFAULT NULL,
  `client_id` varchar(45) DEFAULT NULL,
  `observacao` varchar(45) DEFAULT NULL,
  `quantidade` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `pedidos`
--

INSERT INTO `pedidos` (`id`, `item`, `data_entrega`, `client_id`, `observacao`, `quantidade`) VALUES
(9, 'Bolo de Chocolate', '16/12/2022', 'Cintia', 'Entregar em casa', '1'),
(10, 'Bolo de morango ', '16/12/2022', 'Cintia', 'Entregar em casa', '1'),
(11, 'Torta de limao', '16/12/2022', 'Cintia', 'Entregar em casa', '1');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `estoque`
--
ALTER TABLE `estoque`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `itens`
--
ALTER TABLE `itens`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de tabela `estoque`
--
ALTER TABLE `estoque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `itens`
--
ALTER TABLE `itens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
