INSERT INTO
	EMPLOYEE (first_name, last_name, email)
VALUES
  	('Parijatha','Sudhaker','pari@spr.com'),
  	('Salaam','Bombay','slmbmy@mh.com'),
  	('John', 'Doe', 'xyz@email.com'),
  	('Raju', 'Katta', 'rajuk@email.com');

INSERT INTO
	USERS (user_name, password, active, role_id)
VALUES
  	('parijatha','sudhaker','YES', 2),
  	('salaam','bombay','YES', 3),
  	('john','doe','NO', 4),
  	('raju','k','YES', 5);

INSERT INTO
	ROLE (role_id, name)
VALUES
  	(2,'USER'),
  	(2,'ADMIN'),
  	(3,'USER'),
  	(4,'ADMIN');
