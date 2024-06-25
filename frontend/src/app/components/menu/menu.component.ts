import { Component } from '@angular/core';
import {StorageService} from "../../_service/storage.service";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  constructor(private storageService: StorageService) {}

  logout() {
    this.storageService.logout();
  }
}
