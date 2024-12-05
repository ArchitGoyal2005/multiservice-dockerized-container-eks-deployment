DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'menu_db') THEN
        CREATE DATABASE menu_db;
    END IF;
END
$$;


\c menu_db;

CREATE TABLE Menu (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO Menu (name) VALUES
('burger menu'),
('sajt menu'),
('vega menu');
