import {Component, Input} from '@angular/core';
import {Trip} from "../../_model/trip";

@Component({
  selector: 'app-trip-details',
  standalone: true,
  imports: [],
  templateUrl: './trip-details.component.html',
  styleUrl: './trip-details.component.css'
})
export class TripDetailsComponent {
  @Input({required: true}) trip!: Trip;
}
