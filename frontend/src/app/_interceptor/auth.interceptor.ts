import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {StorageService} from "../_service/storage.service";
import {inject} from "@angular/core";
import {catchError, throwError} from "rxjs";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const storageService : StorageService = inject(StorageService);
  const token = storageService.getUser()['token'];
  if(token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })
  }
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      const currentTime = new Date();
      if ((error && error.status === 0 || error && error.status === 500) && storageService.getExpirationDate() < currentTime) {
        storageService.logout();
      }
      return throwError(() => new Error(error.message || 'An unknown error occurred'));
    })
  );
}
