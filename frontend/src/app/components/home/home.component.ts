import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {Trip} from "../../_model/trip";
import {TripService} from "../../_service/trip.service";
import {UserService} from "../../_service/user.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {TripDetailsComponent} from "../trip-details/trip-details.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    NgForOf,
    TripDetailsComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  nextTrip$! : Observable<Trip>;
  latestTrip$! : Observable<Trip>;
  randomImages$! : Observable<string[]>;

  constructor(private tripService: TripService, private userService: UserService) { }

  ngOnInit() {
    this.nextTrip$ = this.tripService.getNextTrip();
    this.latestTrip$ = this.tripService.getLatestTrip();
    this.randomImages$ = this.userService.getRandomImages(5);
  }
}
