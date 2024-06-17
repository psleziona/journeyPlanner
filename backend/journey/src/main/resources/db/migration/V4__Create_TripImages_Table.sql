create table trip_images(
    trip_id BIGINT NOT NULL ,
    filename varchar(255),
    FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
)