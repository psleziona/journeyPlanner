import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../_model/user";
import {TripImages} from "../_model/trip-images";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = "http://localhost:8080/users/";

  constructor(private http: HttpClient) { }

  getUserPhotos() {
    return this.http.get<TripImages[]>(this.url + "images");
  }

  getUserByUsername(username: string) {
    return this.http.get<User>(this.url + `username/${username}`);
  }

  getRandomImages(count:number) {
    return this.http.get<string[]>(this.url + `photos/random/${count}`);
  }
}
