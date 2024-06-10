CREATE TABLE City (
	city_id			INT				PRIMARY KEY AUTO_INCREMENT,
	name			VARCHAR(50)		NOT NULL
);

CREATE TABLE Station (
	station_id		INT				PRIMARY KEY AUTO_INCREMENT,
	name			VARCHAR(50)		NOT NULL,
	city_id			INT				NOT NULL,

	FOREIGN KEY (city_id) REFERENCES City(city_id)
);

CREATE TABLE Traveler (
	traveler_id		INT				PRIMARY KEY AUTO_INCREMENT,
	first_name		VARCHAR(50)		NOT NULL,
	last_name		VARCHAR(50)		NOT NULL,
	email_addreess	VARCHAR(50)		NOT NULL
);

CREATE TABLE Linestop (
	linestop_id		INT				PRIMARY KEY AUTO_INCREMENT,
	next_linestop	INT,
	distance_next	INT,
	station_id		INT,

	FOREIGN KEY (station_id) REFERENCES Station(station_id),
	FOREIGN KEY (linestop_id) REFERENCES Linestop(linestop_id)
);

CREATE TABLE Line (
	line_id			INT				PRIMARY KEY AUTO_INCREMENT,
	name			VARCHAR(50)		NOT NULL,
	first_stop_id	INT				NOT NULL,

	FOREIGN KEY (first_stop_id) REFERENCES Linestop(linestop_id)
);

CREATE TABLE Locomotive (
	locomotive_id	INT				PRIMARY KEY AUTO_INCREMENT,
	model			VARCHAR(100)	NOT NULL,
	origin_country	VARCHAR(100)	NOT NULL
);

CREATE TABLE Train (
	train_id		INT				PRIMARY KEY AUTO_INCREMENT,
	line_id			INT				NOT NULL,
	locomotive_id	INT				NOT NULL,
	curr_linestop	INT,

	FOREIGN KEY (line_id) REFERENCES Line(line_id),
	FOREIGN KEY (curr_linestop) REFERENCES Linestop(linestop_id),
	FOREIGN KEY (locomotive_id) REFERENCES Locomotive(locomotive_id)
);

CREATE TABLE Ticket (
	ticket_id		INT				PRIMARY KEY AUTO_INCREMENT,
	traveler_id		INT				NOT NULL,
	first_stop		INT				NOT NULL,
	last_stop		INT				NOT NULL,
	train_id		INT				NOT NULL,
	wagon_num		INT				NOT NULL,
	seat_num		INT,
	is_expired		BOOLEAN			DEFAULT TRUE,

	FOREIGN KEY (traveler_id) REFERENCES Traveler(traveler_id),
	FOREIGN KEY (train_id) REFERENCES Train(train_id),
	FOREIGN KEY (first_stop) REFERENCES Station(station_id),
	FOREIGN KEY (last_stop) REFERENCES Station(station_id)
);


CREATE TABLE Wagon (
	wagon_id		INT				PRIMARY KEY	AUTO_INCREMENT,
	wagon_num		INT				NOT NULL,
	wagon_capacity	INT,
	train_id		INT,

	FOREIGN KEY (train_id) REFERENCES Train(train_id)
);



