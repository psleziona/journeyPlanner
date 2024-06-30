import {Component, Inject} from '@angular/core';
import {Observable} from "rxjs";
import {TripImages} from "../../_model/trip-images";
import {UserService} from "../../_service/user.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-photos',
  standalone: true,
  imports: [
    NgForOf,
    AsyncPipe,
    NgIf,
    RouterLink
  ],
  templateUrl: './photos.component.html',
  styleUrl: './photos.component.css'
})
export class PhotosComponent {
  photos$ : Observable<TripImages[]>;
  openImage = "http://localhost:8080/images/myh6jvhL8InBa4veH59q9d8x2MKHHk.png";
  isModalOpen = false;
  modalPosition = '0';

  constructor(private userService: UserService) {
    this.photos$ = this.userService.getUserPhotos();
  }

  showFullPhotoSize(show: boolean, event: Event) {
    this.isModalOpen = show;
    const target = event.target as HTMLImageElement | null;
    if (target && target instanceof HTMLImageElement) {
      this.openImage = target.src;
      this.modalPosition = window.scrollY + 'px';
    }
  }
}
