create table trip_users(
    id_user BIGINT NOT NULL ,
    id_trip BIGINT not null ,
    FOREIGN KEY (id_trip) REFERENCES trips(id) ON DELETE CASCADE,
    foreign key (id_user) references users(id) on delete cascade
)