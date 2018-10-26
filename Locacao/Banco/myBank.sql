-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.5.55-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para locadora_automoveis
CREATE DATABASE IF NOT EXISTS `locadora_automoveis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `locadora_automoveis`;


-- Copiando estrutura para tabela locadora_automoveis.automovel
CREATE TABLE IF NOT EXISTS `automovel` (
  `idAutomovel` int(11) NOT NULL AUTO_INCREMENT,
  `idModelo` int(11) NOT NULL,
  `placa` varchar(10) DEFAULT NULL,
  `cor` varchar(40) DEFAULT NULL,
  `ano` varchar(10) DEFAULT NULL,
  `tipoCombustivel` varchar(30) DEFAULT NULL,
  `kmAtual` double DEFAULT NULL,
  `renavam` varchar(50) DEFAULT NULL,
  `chasis` varchar(50) DEFAULT NULL,
  `vlLocacaoHora` double DEFAULT NULL,
  `vlLocacaoKm` double DEFAULT NULL,
  `situacao` varchar(50) NOT NULL DEFAULT 'disponivel',
  PRIMARY KEY (`idAutomovel`),
  KEY `fk_automovel_modelo` (`idModelo`),
  CONSTRAINT `fk_automovel_modelo` FOREIGN KEY (`idModelo`) REFERENCES `modelo` (`idmodelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.automovel: ~0 rows (aproximadamente)
DELETE FROM `automovel`;
/*!40000 ALTER TABLE `automovel` DISABLE KEYS */;
/*!40000 ALTER TABLE `automovel` ENABLE KEYS */;


-- Copiando estrutura para tabela locadora_automoveis.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.cliente: ~0 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


-- Copiando estrutura para tabela locadora_automoveis.locacao
CREATE TABLE IF NOT EXISTS `locacao` (
  `idlocacao` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) NOT NULL,
  `idautomovel` int(11) NOT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `horaFim` datetime DEFAULT NULL,
  `kmInicio` double DEFAULT NULL,
  `kmFim` double DEFAULT NULL,
  `kmRodado` double DEFAULT NULL,
  `tempoHoras` double DEFAULT NULL,
  `vlHora` double DEFAULT NULL,
  `vlKm` double DEFAULT NULL,
  `valorTotal` double DEFAULT NULL,
  `situacao` varchar(50) NOT NULL DEFAULT 'aberto',
  PRIMARY KEY (`idlocacao`),
  KEY `fk_locacao_automovel` (`idautomovel`),
  KEY `fk_locacao_cliente` (`idcliente`),
  CONSTRAINT `fk_locacao_automovel` FOREIGN KEY (`idautomovel`) REFERENCES `automovel` (`idAutomovel`),
  CONSTRAINT `fk_locacao_cliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.locacao: ~0 rows (aproximadamente)
DELETE FROM `locacao`;
/*!40000 ALTER TABLE `locacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `locacao` ENABLE KEYS */;


-- Copiando estrutura para tabela locadora_automoveis.marca
CREATE TABLE IF NOT EXISTS `marca` (
  `idmarca` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.marca: ~0 rows (aproximadamente)
DELETE FROM `marca`;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;


-- Copiando estrutura para tabela locadora_automoveis.modelo
CREATE TABLE IF NOT EXISTS `modelo` (
  `idmodelo` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `idMarca` int(11) DEFAULT NULL,
  PRIMARY KEY (`idmodelo`),
  KEY `fk_modelo_marca` (`idMarca`),
  CONSTRAINT `fk_modelo_marca` FOREIGN KEY (`idMarca`) REFERENCES `marca` (`idmarca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.modelo: ~0 rows (aproximadamente)
DELETE FROM `modelo`;
/*!40000 ALTER TABLE `modelo` DISABLE KEYS */;
/*!40000 ALTER TABLE `modelo` ENABLE KEYS */;


-- Copiando estrutura para tabela locadora_automoveis.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.usuario: ~1 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idusuario`, `login`, `senha`, `nome`) VALUES
	(1, 'admin', 'admin', 'Lucas Jacobi');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
