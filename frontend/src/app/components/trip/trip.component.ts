import { Component } from '@angular/core';
import {Trip} from "../../_model/trip";
import {TripService} from "../../_service/trip.service";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Observable} from "rxjs";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {StorageService} from "../../_service/storage.service";
import {UserService} from "../../_service/user.service";
import {FormsModule} from "@angular/forms";
import {User} from "../../_model/user";
import {ImageService} from "../../_service/image.service";
import {Comment} from "../../_model/comment";

@Component({
  selector: 'app-trip',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    FormsModule,
    RouterLink
  ],
  templateUrl: './trip.component.html',
  styleUrl: './trip.component.css'
})
export class TripComponent {
  trip$!: Observable<Trip>;
  idTrip!: number;
  username = '';
  newComment = '';
  photo! : File | null;
  openImage = "http://localhost:8080/images/myh6jvhL8InBa4veH59q9d8x2MKHHk.png";
  isModalOpen = false;
  modalPosition = '0';
  constructor(private tripService: TripService,
              private route: ActivatedRoute,
              public storageService: StorageService,
              private userService: UserService,
              private imageService: ImageService) {}


  ngOnInit() {
    this.route.params.subscribe(params => {
      this.idTrip = +params['id'];
      this.trip$ = this.tripService.getTrip(this.idTrip);
    });
  }

  addUser() {
    this.userService.getUserByUsername(this.username)
      .subscribe({
        next: (user : User) => {
          this.tripService.addUserToTrip(this.idTrip, user.id).subscribe({
            next: () => window.location.reload()
          })
        }
      })
  }

  addComment() {
    const tripComment : Comment = {
      idTrip: this.idTrip,
      comment: this.newComment
    }
    this.tripService.addComment(tripComment)
      .subscribe({
        next: res => window.location.reload()
      })
  }

  onFileSelected(event: Event) {
    this.photo = (event.target as HTMLInputElement).files!.item(0);
    if(!this.photo || !this.photo.type.match('image.*')) {
      alert("Wybrany plik nie jest obrazem. Proszę wybrać plik obrazu.");
    }
  }

  addPhoto() {
      const img = new FormData();
      if(this.photo) {
        img.append("file", this.photo);
        img.append("idTrip", this.idTrip.toString());
        this.imageService.uploadImage(img)
          .subscribe(res => {
            window.location.reload()
          });
      }
    }

    showFullPhotoSize(show: boolean, event: Event) {
      this.isModalOpen = show;
      const target = event.target as HTMLImageElement | null;
      if (target && target instanceof HTMLImageElement) {
        this.openImage = target.src;
        this.modalPosition = window.scrollY + 'px';
      }
    }

  protected readonly window = window;
}
