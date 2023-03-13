CREATE TABLE IF NOT EXISTS user_entity (
                                           id SERIAL PRIMARY KEY,
                                           first_name VARCHAR(255) NOT NULL,
                                           last_name VARCHAR(255) NOT NULL,
                                           email VARCHAR(255) NOT NULL UNIQUE,
                                           password VARCHAR(255) NOT NULL,
                                           is_active BOOLEAN NOT NULL DEFAULT false
);