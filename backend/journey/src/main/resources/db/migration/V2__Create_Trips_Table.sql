CREATE TABLE IF NOT EXISTS trips (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    start DATE NOT NULL,
    finish DATE NOT NULL,
    images TEXT[],
    schedules JSONB,
    users JSONB,
    comments TEXT[]
    );