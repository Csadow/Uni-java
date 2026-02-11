CREATE TABLE clothing(
                         clothing_id SERIAL PRIMARY KEY ,
                         name VARCHAR(100) NOT NULL ,
                         price DOUBLE PRECISION NOT NULL,
                         size DECIMAL(20, 60) NOT NULL ,

                         clothing_type VARCHAR(20) NOT NULL CHECK ( clothing_type IN ('SHIRT', 'SHORTS') ),

                         sleeve_length DECIMAL(10, 30),
                         waist_length DECIMAL(10, 50)
);

CREATE INDEX idx_clothing_type ON clothing(clothing_type);
CREATE INDEX idx_clothing_name ON clothing(name);


INSERT INTO clothing(clothing_id, name, price, "size", clothing_type, sleeve_length, waist_length)
VALUES
    (1, 'One Piece', 19.99, 45, 'SHIRT', 20, NULL),
    (2, 'Rick and Morty', 10.55, 42, 'SHIRT', 12, NULL);

INSERT INTO clothing(clothing_id, name, price, "size", clothing_type, sleeve_length, waist_length)
VALUES
    (9, 'Hawaii', 25.0, 32, 'SHORTS', NULL, 14),
    (45, 'Pajamas', 14.99, 15, 'SHORTS', NULL, 15);


SELECT * FROM clothing ORDER BY clothing_id;

SELECT * FROM clothing WHERE clothing_type='SHIRT';

SELECT * FROM clothing WHERE clothing_type='SHORTS';

SELECT * FROM clothing WHERE price<15 ORDER BY price DESC ;

SELECT * FROM clothing WHERE size >20 ORDER BY size DESC ;

SELECT clothing_type, AVG(clothing.price) as avg_salary, COUNT(*) as count
FROM clothing
GROUP BY clothing_type
