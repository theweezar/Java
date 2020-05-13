CREATE DATABASE MUSIC;

USE MUSIC;

CREATE TABLE users(
	id INT PRIMARY KEY IDENTITY(1,1),
	username VARCHAR(20) NOT NULL UNIQUE,
	password TEXT NOT NULL,
	email varchar(100) UNIQUE NOT NULL
)

CREATE TABLE kind(
	id INT PRIMARY KEY IDENTITY(1,1),
	kindName NVARCHAR(30) NOT NULL UNIQUE
)

CREATE TABLE songs(
	id INT PRIMARY KEY IDENTITY(1,1),
	link TEXT NOT NULL,
	userId INT NOT NULL,
	upload_at DATETIME NOT NULL DEFAULT GETDATE(),
	songName NVARCHAR(100) NOT NULL,
	singerName NVARCHAR(100) NOT NULL,
	musicianName NVARCHAR(100) NOT NULL,
	kindId INT NOT NULL,
	_view INT NOT NULL
)

CREATE TABLE comments(
	userId INT NOT NULL,
	songId INT NOT NULL,
	content TEXT NOT NULL,
	comment_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE playlist(
	id INT PRIMARY KEY IDENTITY(1,1),
	userId INT NOT NULL,
	isLater BIT NOT NULL
)

CREATE TABLE playlist_detail(
	id INT PRIMARY KEY IDENTITY(1,1),
	plId INT NOT NULL,
	songId INT NOT NULL,
	add_at DATETIME NOT NULL DEFAULT GETDATE()
)

ALTER TABLE songs ADD FOREIGN KEY (userId) REFERENCES users(id);

ALTER TABLE songs ADD FOREIGN KEY (kindId) REFERENCES kind(id);

ALTER TABLE comments ADD FOREIGN KEY (userId) REFERENCES users(id);

ALTER TABLE comments ADD FOREIGN KEY (songId) REFERENCES songs(id);

ALTER TABLE playlist ADD FOREIGN KEY (userId) REFERENCES users(id);

ALTER TABLE playlist_detail ADD FOREIGN KEY (plId) REFERENCES playlist(id);

ALTER TABLE playlist_detail ADD FOREIGN KEY (songId) REFERENCES songs(id);

/*ALTER TABLE playlist_detail ADD CONSTRAINT PK_SONG PRIMARY KEY(plId, songId);*/

INSERT INTO users VALUES
('admin','admin','admin@gmail.com'),
('phandai','phandai','phandai@gmail.com'),
('trucdong','trucdong','trucdong@gmail.com')


INSERT INTO kind VALUES
('Nhạc Âu Mỹ'),
('Nhạc Bolero'),
('Nhạc EDM'),
('Nhạc Không Lời'),
('Nhạc Rap Việt'),
('Nhạc Thiếu Nhi'),
('Nhạc Trẻ')


ALTER TABLE playlist_detail ADD id INT PRIMARY KEY IDENTITY(1,1)

drop table playlist_detail