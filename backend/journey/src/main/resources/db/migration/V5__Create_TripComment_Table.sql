create table trip_comments(
    trip_id BIGINT NOT NULL ,
    comment varchar(255),
    FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
)