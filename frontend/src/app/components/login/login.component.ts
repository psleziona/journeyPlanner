import { Component } from '@angular/core';
import {StorageService} from "../../_service/storage.service";
import {AuthService} from "../../_service/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

  constructor(private storageService: StorageService, private authService: AuthService, private router: Router) {}

  login() {
    const body = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    }
    this.authService.login(body).subscribe({
      next: x => {
        const tokenParts = x['token'].split('.');
        const encodedPayload = tokenParts[1];
        const decodedPayload = JSON.parse(atob(encodedPayload));
        decodedPayload['token'] = x['token'];
        this.storageService.saveUser(decodedPayload);
        this.router.navigateByUrl("/");
      },
      error: err => alert("Błędne dane")
    });
  }
}
