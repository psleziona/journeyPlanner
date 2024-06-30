import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {TripService} from "../../_service/trip.service";
import {Trip} from "../../_model/trip";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-trip',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './add-trip.component.html',
  styleUrl: './add-trip.component.css'
})
export class AddTripComponent {
  addTripForm! : FormGroup;

  constructor(private fb: FormBuilder, private tripService : TripService, private router: Router) { }

  ngOnInit() {
    this.addTripForm = this.fb.group({
      name: ['', Validators.required],
      start: ['', Validators.required],
      finish: ['', Validators.required]
    })
  }

  addTrip() {
    if(this.addTripForm.valid) {
      const trip : Trip = {
        name: this.addTripForm.value.name,
        start: this.addTripForm.value.start,
        finish: this.addTripForm.value.finish,
      }
      this.tripService.addTrip(trip).subscribe({
        next: (createdTrip: Trip) => {
          this.router.navigateByUrl(`trip/${createdTrip.id}`);
        },
        error: (err) => {
          console.error(err);
        }
      });
    }
  }
}
