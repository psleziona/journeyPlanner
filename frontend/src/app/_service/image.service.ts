import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  url = 'http://localhost:8080/images/upload';
  constructor(private http: HttpClient) { }

  uploadImage(img: FormData) {
    return this.http.post(this.url, img);
  }
}
