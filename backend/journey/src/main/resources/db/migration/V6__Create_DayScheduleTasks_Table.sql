create table day_schedules_tasks(
    id_day_schedule BIGINT NOT NULL ,
    id_user bigint not null,
    task varchar(255),
    FOREIGN KEY (id_day_schedule) REFERENCES day_schedules(id) ON DELETE CASCADE,
    foreign key (id_user) references users(id) on delete cascade
)