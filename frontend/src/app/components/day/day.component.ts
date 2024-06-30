import {Component} from '@angular/core';
import {DayScheduleService} from "../../_service/day-schedule.service";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import {DaySchedule} from "../../_model/day-schedule";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {DayScheduleTask} from "../../_model/day-schedule-task";

@Component({
  selector: 'app-day',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    NgForOf,
    FormsModule
  ],
  templateUrl: './day.component.html',
  styleUrl: './day.component.css'
})
export class DayComponent {
  daySchedule$!: Observable<DaySchedule>;
  newTask = '';
  idSchedule! : number;
  constructor(private dayScheduleService : DayScheduleService,
              private router: ActivatedRoute) {}

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.idSchedule = +params['id'];
      this.daySchedule$ = this.dayScheduleService.getDaySchedule(this.idSchedule);
    })
  }

  addTask() {
    const dayScheduleTask : DayScheduleTask = {
      idDaySchedule: this.idSchedule,
      task: this.newTask
    }
    this.dayScheduleService.addTaskToDaySchedule(dayScheduleTask)
      .subscribe({
        next: res => {
          window.location.reload();
        }
      })
  }
}
