import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Trip} from "../_model/trip";

@Injectable({
  providedIn: 'root'
})
export class TripService {
  private url = 'http://localhost:8080/trips'
  constructor(private http: HttpClient) { }

  getTrips() {
    return this.http.get<Trip[]>(this.url);
  }
}
