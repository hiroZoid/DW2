DROP DATABASE saude;
CREATE DATABASE saude;
USE saude;

/*============================================================================*/

CREATE TABLE IF NOT EXISTS ambulat (
  NROA int(11) NOT NULL,
  ANDAR int(11) DEFAULT NULL,
  CAPACIDADE int(11) DEFAULT NULL,
  PRIMARY KEY (NROA)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO ambulat (NROA, ANDAR, CAPACIDADE) VALUES
	(1, 1, 30),
	(2, 1, 50),
	(3, 2, 40),
	(4, 2, 25),
	(5, 2, 55);

/*============================================================================*/

CREATE TABLE IF NOT EXISTS funcionario (
  CODF int(11) NOT NULL,
  NOMEF varchar(30) DEFAULT NULL,
  IDADE int(11) DEFAULT NULL,
  SALARIO int(11) DEFAULT NULL,
  NROA int(11) DEFAULT NULL,
  PRIMARY KEY (CODF),
  KEY FK1_nroa (NROA),
  CONSTRAINT FK1_nroa FOREIGN KEY (NROA) REFERENCES ambulat (NROA)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO funcionario (CODF, NOMEF, IDADE, SALARIO, NROA) VALUES
	(1, 'Renata', 32, 1200, 2),
	(2, 'Mariana', 55, 1220, 1),
	(3, 'Caio', 45, 1100, 5),
	(4, 'Silvio', 44, 1200, 4),
	(5, 'Patricia', 33, 2500, 3),
	(6, 'Eva', 38, 1600, 5);

/*============================================================================*/

CREATE TABLE IF NOT EXISTS medico (
  CODM int(11) NOT NULL,
  NOMEM varchar(30) DEFAULT NULL,
  IDADE int(2) DEFAULT NULL,
  ESPECIALIDADE varchar(20) DEFAULT NULL,
  CIDADE varchar(25) DEFAULT NULL,
  ESTADO varchar(2) DEFAULT NULL,
  NROA int(11) NOT NULL,
  PRIMARY KEY (CODM),
  KEY NROA (NROA),
  CONSTRAINT medico_ibfk_1 FOREIGN KEY (NROA) REFERENCES ambulat (NROA)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO medico (CODM, NOMEM, IDADE, ESPECIALIDADE, CIDADE, ESTADO, NROA) VALUES
	(1, 'Joao', 40, 'Ortopedia', 'Florianopolis', 'SC', 1),
	(2, 'Maria', 42, 'Traumatologia', 'Blumenau', 'SC', 2),
	(3, 'Pedro', 51, 'Pediatria', 'Sao Jose', 'SC', 2),
	(4, 'Carlos', 28, 'Ortopedia', 'Joinvile', 'SC', 4),
	(5, 'Marcia', 33, 'Neurologia', 'Blumenau', 'SC', 3);

/*============================================================================*/

CREATE TABLE IF NOT EXISTS paciente (
  CODP int(11) NOT NULL,
  NOMEP varchar(30) DEFAULT NULL,
  IDADE int(2) DEFAULT NULL,
  CIDADEP varchar(25) DEFAULT NULL,
  PLANO varchar(15) DEFAULT NULL,
  PRIMARY KEY (CODP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO paciente (CODP, NOMEP, IDADE, CIDADEP, PLANO) VALUES
	(1, 'Ana', 20, 'Florianopolis', 'SUS'),
	(2, 'Paulo', 24, 'Blumenau', 'Unimed'),
	(3, 'Lucia', 30, 'Blumenau', 'SUS'),
	(4, 'Gabriela', 29, 'Joinvile', 'Unimed');

/*============================================================================*/

CREATE TABLE IF NOT EXISTS consulta (
  CODM int(11) NOT NULL,
  CODP int(11) NOT NULL,
  DATA date NOT NULL,
  PRIMARY KEY (CODM,CODP,DATA),
  KEY FK2_codp (CODP),
  CONSTRAINT FK1_codmed FOREIGN KEY (CODM) REFERENCES medico (CODM),
  CONSTRAINT FK2_codp FOREIGN KEY (CODP) REFERENCES paciente (CODP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO consulta (CODM, CODP, DATA) VALUES
	(1, 1, '2011-09-22'),
	(2, 1, '2011-09-23'),
	(2, 2, '2011-09-23'),
	(4, 2, '2011-09-28'),
	(5, 2, '2011-09-30'),
	(2, 3, '2011-09-26'),
	(3, 3, '2011-09-27'),
	(1, 4, '2011-09-23'),
	(2, 4, '2011-09-26'),
	(3, 4, '2011-09-26'),
	(3, 4, '2011-09-27'),
	(4, 4, '2011-09-28');

commit;