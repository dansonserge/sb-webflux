CREATE TABLE IF NOT EXISTS user_entity (
                   id SERIAL PRIMARY KEY,
                   firstName VARCHAR(255) NOT NULL,
                   lastName VARCHAR(255) NOT NULL,
                   email VARCHAR(255) NOT NULL UNIQUE,
                   password VARCHAR(255) NOT NULL,
                   status BOOLEAN NOT NULL DEFAULT false
                );