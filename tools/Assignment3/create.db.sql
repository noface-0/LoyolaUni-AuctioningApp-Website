CREATE DATABASE IF NOT EXISTS AnimDB;
USE AnimDB;
DROP TABLE IF EXISTS Animals;
CREATE TABLE IF NOT EXISTS Animals (
    id INT NOT NULL,
    Name VARCHAR(50),
    Size VARCHAR(50),
    Age INT NOT NULL,
    Intelligence VARCHAR(50));
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (0,"Human","medium",79,"high");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (1,"Dog","small",12,"low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (2,"Cat","small",16,"mid to low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (3,"Bear","large",34,"low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (4,"Tiger","larger",18,"mid to low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (5,"Cow","big",22,"mid to low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (6,"Lion","big",14,"low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (7,"Cheetah","medium",12,"low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (8,"Shark","big",30,"low");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (9,"Cuttlefish","tiny",2,"very little if any");
INSERT INTO Animals (id, Name, Size, Age, Intelligence) VALUES (10,"Elephant","massive",70,"mid");