create table trip_comments(
    id_trip BIGINT NOT NULL ,
    id_user bigint not null ,
    comment varchar(255),
    FOREIGN KEY (id_trip) REFERENCES trips(id) ON DELETE CASCADE,
    foreign key (id_user) references users(id) on delete cascade
)