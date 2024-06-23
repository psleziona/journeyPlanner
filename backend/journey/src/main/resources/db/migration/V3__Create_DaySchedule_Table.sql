CREATE TABLE IF NOT EXISTS day_schedules (
     id SERIAL PRIMARY KEY,
     id_trip BIGINT NOT NULL,
     date DATE NOT NULL,
     FOREIGN KEY (id_trip) REFERENCES trips(id) ON DELETE CASCADE
    );