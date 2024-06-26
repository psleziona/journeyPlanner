import {Component, Inject} from '@angular/core';
import {Observable} from "rxjs";
import {TripImages} from "../../_model/trip-images";
import {UserService} from "../../_service/user.service";
import {AsyncPipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-photos',
  standalone: true,
  imports: [
    NgForOf,
    AsyncPipe
  ],
  templateUrl: './photos.component.html',
  styleUrl: './photos.component.css'
})
export class PhotosComponent {
  photos$ : Observable<TripImages[]>;

  constructor(private userService: UserService) {
    this.photos$ = this.userService.getUserPhotos();
  }
}
