CREATE TABLE Cities (
	city_id			INT				PRIMARY KEY AUTO_INCREMENT,
	name			VARCHAR(50)		NOT NULL
);

CREATE TABLE Stations (
	station_id		INT				PRIMARY KEY AUTO_INCREMENT,
	name			VARCHAR(50)		NOT NULL UNIQUE,
	city_id			INT				NOT NULL,

	FOREIGN KEY (city_id) REFERENCES Cities(city_id)
);

CREATE TABLE Travelers (
	traveler_id		INT				PRIMARY KEY AUTO_INCREMENT,
	first_name		VARCHAR(50)		NOT NULL,
	last_name		VARCHAR(50)		NOT NULL,
	mail_address	VARCHAR(50)		NOT NULL
);

CREATE TABLE Linestops (
	linestop_id		INT				PRIMARY KEY AUTO_INCREMENT,
	next_linestop	INT,
	distance_next	INT,
	station_id		INT				NOT NULL,

	FOREIGN KEY (station_id) REFERENCES Stations(station_id),
	FOREIGN KEY (next_linestop) REFERENCES Linestops(linestop_id)
);

CREATE TABLE Lines (
	line_id			INT				PRIMARY KEY AUTO_INCREMENT,
	name			VARCHAR(50)		NOT NULL UNIQUE,
	first_stop_id	INT				NOT NULL,

	FOREIGN KEY (first_stop_id) REFERENCES Linestops(linestop_id)
);

CREATE TABLE Locomotives (
	locomotive_id	INT				PRIMARY KEY AUTO_INCREMENT,
	model			VARCHAR(100)	NOT NULL,
	origin_country	VARCHAR(100)	NOT NULL
);

CREATE TABLE Trains (
	train_id		INT				PRIMARY KEY AUTO_INCREMENT,
	line_id			INT				NOT NULL,
	locomotive_id	INT				NOT NULL,
	curr_linestop	INT,

	FOREIGN KEY (line_id) REFERENCES Lines(line_id),
	FOREIGN KEY (curr_linestop) REFERENCES Linestops(linestop_id),
	FOREIGN KEY (locomotive_id) REFERENCES Locomotives(locomotive_id)
);

CREATE TABLE Tickets (
	ticket_id		INT				PRIMARY KEY AUTO_INCREMENT,
	traveler_id		INT				NOT NULL,
	first_stop		INT				NOT NULL,
	last_stop		INT				NOT NULL,
	train_id		INT				NOT NULL,
	wagon_num		INT				NOT NULL,
	seat_num		INT,
	is_expired		BOOLEAN			DEFAULT FALSE,

	FOREIGN KEY (traveler_id) REFERENCES Travelers(traveler_id),
	FOREIGN KEY (train_id) REFERENCES Trains(train_id),
	FOREIGN KEY (first_stop) REFERENCES Stations(station_id),
	FOREIGN KEY (last_stop) REFERENCES Stations(station_id)
);


CREATE TABLE Wagons (
	wagon_id		INT				PRIMARY KEY	AUTO_INCREMENT,
	wagon_num		INT				NOT NULL,
	wagon_capacity	INT,
	train_id		INT,

	FOREIGN KEY (train_id) REFERENCES Trains(train_id)
);



