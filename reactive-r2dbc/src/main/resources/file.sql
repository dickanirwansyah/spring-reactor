CREATE TABLE users ( id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, name VARCHAR(100) NOT NULL, age integer,salary decimal);
DROP TABLE IF EXISTS department ;
CREATE TABLE department ( id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,user_id integer, name VARCHAR(100) NOT NULL, loc VARCHAR(100));