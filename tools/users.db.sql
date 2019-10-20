USE jajeimo;
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(320),
    Password VARCHAR(255),
    Created DATETIME,
    Modified DATETIME);
INSERT INTO users (id, FirstName, LastName, Email, Password, Created, Modified) VALUES (0, "javonTest", "KitsonTest", "jckitson@loyola.edu", 123456, CURDATE(),CURDATE()); 
