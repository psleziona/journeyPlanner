CREATE TABLE IF NOT EXISTS trips (
    id SERIAL PRIMARY KEY,
    id_owner BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    start DATE NOT NULL,
    finish DATE NOT NULL,
    foreign key(id_owner) references users(id) on delete cascade
    );