import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  login(body: Object) {
    return this.http.post<any>(this.authUrl + "/login", body);
  }

  register(client: Object) {
    return this.http.post<any>(this.authUrl + "/register", client);
  }
}
