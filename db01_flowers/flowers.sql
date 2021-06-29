-- flowers database
-- created at: 3/3/21
-- author(s): Alejandro Rojas




CREATE TABLE Zones (

	id INT(2) NOT NULL PRIMARY KEY,
	lowerTemp INT,
	higherTemp INT   
);
INSERT INTO Zones VALUES(2,-50,-40);

INSERT INTO Zones VALUES(3, -40, -30);

INSERT INTO Zones VALUES(4, -30, -20);

INSERT INTO Zones VALUES(5, -20, -10);

INSERT INTO Zones VALUES(6, -10, 0);

INSERT INTO Zones VALUES(7, 0, 10);

INSERT INTO Zones VALUES(8, 10, 20);

INSERT INTO Zones VALUES(9, 20, 30);

INSERT INTO Zones VALUES(10, 30, 40);


CREATE TABLE Deliveries(

    id INT(1) NOT NULL PRIMARY KEY,
    categ VARCHAR(5),
    delSize DECIMAL(9, 3)
);

INSERT INTO Deliveries VALUES(1, 'pot', 1.500);

INSERT INTO Deliveries VALUES(2, 'pot', 2.250);

INSERT INTO Deliveries VALUES(3, 'pot', 2.625);

INSERT INTO Deliveries VALUES(4, 'pot', 4.250);

INSERT INTO Deliveries VALUES(5, 'plant', NULL);

INSERT INTO Deliveries VALUES(6, 'bulb', NULL);

INSERT INTO Deliveries VALUES(7, 'hedge', 18.000);

INSERT INTO Deliveries VALUES(8, 'shrub', 24.000);

INSERT INTO Deliveries VALUES(9, 'tree', 36.000);

select * from Deliveries;

CREATE TABLE FlowerInfo(

    id INT(3) NOT NULL PRIMARY KEY,
    commonName VARCHAR(30),
    latinName VARCHAR(35),
    coolZone INT(2),
    hotZone INT(2),
    deliver INT(1),
    sunNeeds  VARCHAR(5),
    FOREIGN KEY(coolZone) REFERENCES Zones(id),
    FOREIGN KEY(hotZone) REFERENCES Zones(id),
    FOREIGN KEY(deliver) REFERENCES Deliveries(id)
);

INSERT INTO FlowerInfo VALUES(101, 'Lady Fern', 'Atbyrium filix-femina', 2, 9, 5, 'SH');

INSERT INTO FlowerInfo VALUES(102, 'Pink Caladiums', 'C.x bortulanum', 10, 10, 6, 'PtoSH');

INSERT INTO FlowerInfo VALUES(103, 'Lily-of-the-Valley', 'Convallaria majalis', 2, 8, 5, 'PtoSH');

INSERT INTO FlowerInfo VALUES(105, 'Purple Liatris', 'Liatris spicata', 3, 9, 6, 'StoP');

INSERT INTO FlowerInfo VALUES(106, 'Black Eyed Susan', 'Rudbeckia fulgida var. specios', 4, 10, 2, 'StoP');

INSERT INTO FlowerInfo VALUES(107, 'Nikko Blue Hydrangea', 'Hydrangea macrophylla', 5, 9, 4, 'StoSH');

INSERT INTO FlowerInfo VALUES(108, 'Variegated Weigela', 'W. florida Variegata', 4, 9, 8, 'StoP');

INSERT INTO FlowerInfo VALUES(110, 'Lombardy Poplar', 'Populus nigra Italica', 3, 9, 9, 'S');

INSERT INTO FlowerInfo VALUES(111, 'Purple Leaf Plum Hedge', 'Prunus x cistena', 2, 8, 7, 'S');

INSERT INTO FlowerInfo VALUES(114, 'Thorndale Ivy', 'Hedera belix Thorndale', 3, 9, 1, 'StoSH');

select * from FlowerInfo;

-- database creation and use

-- tables creation satisfying all of the requirements

-- tables population

-- a) the total number of zones.
SELECT COUNT(*) FROM Zones;

-- b) the number of flowers per cool zone.
SELECT coolZone, COUNT(*) AS "No of Flowers" FROM FlowerInfo GROUP BY coolZone;

-- c) common names of the plants that have delivery sizes less than 5.
SELECT commonName FROM FlowerInfo, Deliveries WHERE FlowerInfo.deliver = Deliveries.id AND delSize < 5;

-- d) common names of the plants that require full sun (i.e., sun needs contains ‘S’).
SELECT commonName FROM FlowerInfo WHERE sunNeeds = 'S';

-- e) all delivery category names order alphabetically (without repetition).
SELECT DISTINCT categ FROM Deliveries ORDER BY categ;

-- f) the exact output (see instructions)
SELECT FlowerInfo.commonName As Name, Zones.lowerTemp AS 'Cool Zone (low)', Zones.higherTemp AS 'Cool Zone (high)', Deliveries.categ As 'Delivery Category' 
FROM FlowerInfo 
INNER JOIN Deliveries ON FlowerInfo.deliver = Deliveries.id INNER JOIN Zones ON FlowerInfo.coolZone = Zones.id order by commonName;

-- g) plant names that have the same hot zone as “Pink Caladiums” (your solution MUST get the hot zone of “Pink Caladiums” in a variable).

SELECT hotZone, COUNT(*) AS "Pink Caladiums" FROM FlowerInfo GROUP BY hotZone;

-- h) the total number of plants, the minimum delivery size, the maximum delivery size, and the average size based on the plants that have delivery sizes (note that the average value should be rounded using two decimals).
SELECT count(*),min(Deliveries.delSize), max(Deliveries.delSize),FORMAT(avg(Deliveries.delSize),2)
FROM FlowerInfo
INNER JOIN Deliveries on FlowerInfo.deliver = Deliveries.id 
WHERE Deliveries.delSize is not null;

-- i) the Latin name of the plant that has the word ‘Eyed’ in its name (you must use LIKE in this query to get full credit).  
SELECT * FROM latinName WHERE FlowerInfo LIKE 'Eyed%';

-- j) the exact output (see instructions)
SELECT Deliveries.categ As 'Category', FlowerInfo.commonName As 'Name' 
FROM FlowerInfo 
INNER JOIN Deliveries ON FlowerInfo.deliver = Deliveries.id order by categ, commonName;