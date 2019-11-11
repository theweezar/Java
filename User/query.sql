use QLKS;

create table users(
	username varchar(100) PRIMARY KEY,
	pass varchar(100) NOT NULL,
	fullname varchar(100) NOT NULL,
	pnumber varchar(20) NOT NULL,
	email varchar(100) UNIQUE NOT NULL,
	administrator bit NOT NULL,
)

drop table users