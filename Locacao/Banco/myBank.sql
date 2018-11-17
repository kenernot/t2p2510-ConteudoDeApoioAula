-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.21 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.5.0.5287
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
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
  `vlLocacaoHora` double DEFAULT '1',
  `vlLocacaoKm` double DEFAULT '1',
  `situacao` varchar(50) NOT NULL DEFAULT 'livre',
  PRIMARY KEY (`idAutomovel`),
  KEY `fk_automovel_modelo` (`idModelo`),
  CONSTRAINT `fk_automovel_modelo` FOREIGN KEY (`idModelo`) REFERENCES `modelo` (`idmodelo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.automovel: ~2 rows (aproximadamente)
DELETE FROM `automovel`;
/*!40000 ALTER TABLE `automovel` DISABLE KEYS */;
INSERT INTO `automovel` (`idAutomovel`, `idModelo`, `placa`, `cor`, `ano`, `tipoCombustivel`, `kmAtual`, `renavam`, `chasis`, `vlLocacaoHora`, `vlLocacaoKm`, `situacao`) VALUES
	(2, 1, 'BLU', '', '', '', 13, '', '', 50, 100, 'locado'),
	(3, 7, 'ACS', '', '', '', 10, '', '', 100, 200, 'locado');
/*!40000 ALTER TABLE `automovel` ENABLE KEYS */;

-- Copiando estrutura para tabela locadora_automoveis.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.cliente: ~2 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`idcliente`, `nome`) VALUES
	(5, 'Lucas'),
	(6, 'Preto');
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
  `kmRodado` double DEFAULT '0',
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.locacao: ~0 rows (aproximadamente)
DELETE FROM `locacao`;
/*!40000 ALTER TABLE `locacao` DISABLE KEYS */;
INSERT INTO `locacao` (`idlocacao`, `idcliente`, `idautomovel`, `horaInicio`, `horaFim`, `kmInicio`, `kmFim`, `kmRodado`, `tempoHoras`, `vlHora`, `vlKm`, `valorTotal`, `situacao`) VALUES
	(33, 5, 3, '2018-11-17 19:29:47', NULL, 10, NULL, 0, NULL, NULL, NULL, NULL, 'aberto'),
	(34, 6, 2, '2018-11-17 19:30:14', '2018-11-17 19:32:25', 11, 12, 1, 0, 0, 100, 100, 'fechado'),
	(35, 5, 2, '2018-11-17 19:50:12', '2018-11-17 19:50:37', 12, 13, 1, 0, 0, 100, 100, 'fechado'),
	(36, 5, 2, '2018-11-17 19:52:34', NULL, 13, NULL, 0, NULL, NULL, NULL, NULL, 'aberto');
/*!40000 ALTER TABLE `locacao` ENABLE KEYS */;

-- Copiando estrutura para tabela locadora_automoveis.marca
CREATE TABLE IF NOT EXISTS `marca` (
  `idmarca` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.marca: ~8 rows (aproximadamente)
DELETE FROM `marca`;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` (`idmarca`, `titulo`) VALUES
	(4, 'teste'),
	(5, 'teste3'),
	(6, 'testee4'),
	(7, 'teste5'),
	(8, 'test8'),
	(9, 'teste4356'),
	(10, 'Chevrolet'),
	(11, 'Fiat');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;

-- Copiando estrutura para tabela locadora_automoveis.modelo
CREATE TABLE IF NOT EXISTS `modelo` (
  `idmodelo` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `idMarca` int(11) DEFAULT NULL,
  PRIMARY KEY (`idmodelo`),
  KEY `fk_modelo_marca` (`idMarca`),
  CONSTRAINT `fk_modelo_marca` FOREIGN KEY (`idMarca`) REFERENCES `marca` (`idmarca`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.modelo: ~6 rows (aproximadamente)
DELETE FROM `modelo`;
/*!40000 ALTER TABLE `modelo` DISABLE KEYS */;
INSERT INTO `modelo` (`idmodelo`, `titulo`, `idMarca`) VALUES
	(1, 'Uno', 11),
	(2, 'Strada', 11),
	(3, 'Unera', 11),
	(4, 'unozera', 11),
	(5, 'teste', 4),
	(7, 'Astra', 10);
/*!40000 ALTER TABLE `modelo` ENABLE KEYS */;

-- Copiando estrutura para tabela locadora_automoveis.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nivel` char(2) DEFAULT '99',
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela locadora_automoveis.usuario: ~1 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idusuario`, `login`, `senha`, `nome`, `nivel`) VALUES
	(1, 'admin', 'admin', 'Lucas Jacobi', '99');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando estrutura para trigger locadora_automoveis.locacao_before_delete
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER';
DELIMITER //
CREATE TRIGGER `locacao_before_delete` BEFORE DELETE ON `locacao` FOR EACH ROW BEGIN
	update automovel set automovel.situacao = 'livre' where automovel.idAutomovel = OLD.idautomovel;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger locadora_automoveis.locacao_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER';
DELIMITER //
CREATE TRIGGER `locacao_before_insert` BEFORE INSERT ON `locacao` FOR EACH ROW BEGIN
	declare P_vlHora double;
	declare P_vlKm double;
	declare P_kmAtual double;
	 

	set P_kmAtual = (select automovel.kmAtual from automovel where automovel.idAutomovel = NEW.idautomovel);
	
	set NEW.horaInicio = NOW();

	SET NEW.kmInicio = P_kmAtual;
	
	update automovel set automovel.situacao = 'locado' where automovel.idAutomovel = NEW.idautomovel;
	if (NEW.situacao = 'fechado') then
	
		set NEW.horaFim = NOW();
		set NEW.kmRodado = NEW.kmFim - NEW.kmInicio;
		set NEW.tempoHoras = HOUR(TIMEDIFF(NEW.horaFim,NEW.horaInicio));
		
		set P_vlHora = (select automovel.vlLocacaoHora from automovel where automovel.idAutomovel = NEW.idautomovel);
		set P_vlHora = P_vlHora * NEW.tempoHoras;
		set P_vlKm = (select automovel.vlLocacaoKm from automovel where automovel.idAutomovel = NEW.idautomovel);
		set P_vlKm = P_vlKm * NEW.kmRodado;
		
		set NEW.valorTotal = P_vlHora + P_vlKm;
		
		set NEW.vlHora = P_vlHora;
		set NEW.vlKm = P_vlKm;
		
		
		update automovel set automovel.kmAtual = NEW.kmFim, automovel.situacao = 'livre' where automovel.idAutomovel = NEW.idautomovel;
		
		
	end if;
	
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger locadora_automoveis.locacao_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER';
DELIMITER //
CREATE TRIGGER `locacao_before_update` BEFORE UPDATE ON `locacao` FOR EACH ROW BEGIN
		declare P_vlHora double;
		declare P_vlKm double;
		
	if (NEW.situacao = 'fechado') then
	
		set NEW.horaFim = NOW();
		set NEW.kmRodado = NEW.kmFim - NEW.kmInicio;
		set NEW.tempoHoras = HOUR(TIMEDIFF(NEW.horaFim,NEW.horaInicio));
		
		set P_vlHora = (select automovel.vlLocacaoHora from automovel where automovel.idAutomovel = NEW.idautomovel);
		set P_vlHora = P_vlHora * NEW.tempoHoras;
		set P_vlKm = (select automovel.vlLocacaoKm from automovel where automovel.idAutomovel = NEW.idautomovel);
		set P_vlKm = P_vlKm * NEW.kmRodado;
		
		set NEW.valorTotal = P_vlHora + P_vlKm;
		
		set NEW.vlHora = P_vlHora;
		set NEW.vlKm = P_vlKm;
		
		
		update automovel set automovel.kmAtual = NEW.kmFim, automovel.situacao = 'livre' where automovel.idAutomovel = NEW.idautomovel;
		
		
	end if;
	
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
