import {CanActivateFn, Router} from '@angular/router';
import {StorageService} from "../_service/storage.service";
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {
  const storageService : StorageService = inject(StorageService);
  const router:Router = inject(Router);
  const isUserLoggedIn = storageService.isLoggedIn();
  if(isUserLoggedIn)
    return true;
  else
    router.navigateByUrl("/login");
  return false;
};
