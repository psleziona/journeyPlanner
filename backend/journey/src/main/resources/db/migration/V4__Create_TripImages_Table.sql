create table trip_images(
    id_trip BIGINT NOT NULL ,
    id_owner bigint not null ,
    filename varchar(255),
    FOREIGN KEY (id_trip) REFERENCES trips(id) ON DELETE CASCADE,
    foreign key (id_owner) references users(id) on delete cascade
)