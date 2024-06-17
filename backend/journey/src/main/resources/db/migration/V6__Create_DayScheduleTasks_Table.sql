create table day_schedules_tasks(
    day_schedule_id BIGINT NOT NULL ,
    task varchar(255),
    FOREIGN KEY (day_schedule_id) REFERENCES day_schedules(id) ON DELETE CASCADE
)