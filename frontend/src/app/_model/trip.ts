import {DaySchedule} from "./day-schedule";
import {User} from "./user";

export interface Trip {
  id?: string,
  name:string,
  idOwner?: number,
  start:string,
  finish:string,
  images?:string[],
  schedules?:DaySchedule[],
  users?:User[],
  comments?:string[]
}
