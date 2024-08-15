-- Create the database
CREATE DATABASE IF NOT EXISTS MyLetterboxd;

-- Switch to the created database
USE MyLetterboxd;

-- Create the MovieInfo table
CREATE TABLE IF NOT EXISTS MovieInfo (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Director VARCHAR(255),
    YearReleased INT,
    GlobalRating FLOAT
);

-- Create the MyDiary table
CREATE TABLE IF NOT EXISTS MyDiary (
    EntryID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    DateWatched DATE NOT NULL,
    MyRating FLOAT NOT NULL,
    FirstWatch BOOLEAN NOT NULL
);
