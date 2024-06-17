create table trip_users(
    trip_id BIGINT NOT NULL ,
    user_id BIGINT not null ,
    FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE,
    foreign key (user_id) references users(id) on delete cascade
)