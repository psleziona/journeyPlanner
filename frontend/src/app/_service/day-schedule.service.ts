import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DaySchedule} from "../_model/day-schedule";
import {DayScheduleTask} from "../_model/day-schedule-task";

@Injectable({
  providedIn: 'root'
})
export class DayScheduleService {
  url = 'http://localhost:8080/schedules/'
  constructor(private http: HttpClient) { }

  getDaySchedule(idDaySchedule: number) {
    return this.http.get<DaySchedule>(this.url + idDaySchedule);
  }

  addTaskToDaySchedule(dayScheduleTask: DayScheduleTask) {
    return this.http.post(this.url + 'task/add', dayScheduleTask);
  }
}
