import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {StorageService} from "../../_service/storage.service";
import {AuthService} from "../../_service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

  constructor(private storageService: StorageService, private authService: AuthService, private router: Router) {}

  register() {
    const body = {
      username: this.registerForm.value.username,
      password: this.registerForm.value.password
    }
    this.authService.register(body).subscribe({
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
