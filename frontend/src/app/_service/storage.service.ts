import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  loggedIn = new Subject<boolean>();
  constructor() { }

  public logout() {
    window.sessionStorage.clear();
    window.location.reload();
    this.loggedIn.next(false);
  }

  public saveUser(user: any) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    this.loggedIn.next(true);
  }

  public getUser() {
    const user = window.sessionStorage.getItem(USER_KEY);
    if(user)
      return JSON.parse(user);
    return {};
  }

  public getUserId() {
    return this.getUser()['sub'];
  }

  public getExpirationDate() {
    return new Date(this.getUser()['exp'] * 1000);
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    return !!user;

  }
}
