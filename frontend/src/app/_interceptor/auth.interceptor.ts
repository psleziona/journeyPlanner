import { HttpInterceptorFn } from '@angular/common/http';
import {StorageService} from "../_service/storage.service";
import {inject} from "@angular/core";

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
  return next(req);
};
