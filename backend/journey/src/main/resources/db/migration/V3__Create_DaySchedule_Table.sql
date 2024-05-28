CREATE TABLE IF NOT EXISTS day_schedules (
                                             id SERIAL PRIMARY KEY,
                                             trip_id BIGINT NOT NULL,
                                             date DATE NOT NULL,
                                             tasks TEXT[],
                                             FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
    );