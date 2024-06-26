import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {Trip} from "../../_model/trip";
import {TripService} from "../../_service/trip.service";
import {AsyncPipe, NgForOf} from "@angular/common";
import {TripDetailsComponent} from "../trip-details/trip-details.component";

@Component({
  selector: 'app-trips',
  standalone: true,
  imports: [
    NgForOf,
    AsyncPipe,
    TripDetailsComponent
  ],
  templateUrl: './trips.component.html',
  styleUrl: './trips.component.css'
})
export class TripsComponent {
  trips$ : Observable<Trip[]>

  constructor(private tripService: TripService) {
    this.trips$ = this.tripService.getTrips();
  }
}
