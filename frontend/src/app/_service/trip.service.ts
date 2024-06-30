import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Trip} from "../_model/trip";
import {Comment} from "../_model/comment";

@Injectable({
  providedIn: 'root'
})
export class TripService {
  private url = 'http://localhost:8080/trips'
  constructor(private http: HttpClient) { }

  getTrips() {
    return this.http.get<Trip[]>(this.url);
  }

  getTrip(idTrip:number) {
    return this.http.get<Trip>(`${this.url}/${idTrip}`)
  }

  addTrip(trip: Trip) {
    return this.http.post<Trip>(this.url, trip);
  }

  addUserToTrip(idTrip:number, idUser:number) {
    return this.http.post(this.url + `/user/add/${idTrip}/${idUser}`, {});
  }

  addComment(comment: Comment) {
    return this.http.post(this.url + '/comments/add', comment);
  }

  getNextTrip() {
    return this.http.get<Trip>(this.url + '/next');
  }

  getLatestTrip() {
    return this.http.get<Trip>(this.url + '/latest');
  }
}
