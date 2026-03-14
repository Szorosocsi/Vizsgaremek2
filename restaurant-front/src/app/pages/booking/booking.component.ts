import { Component } from '@angular/core'
import { ReservationService } from '../../services/reservation.service'
import { Reservation } from '../../models/reservation'

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {

  reservation: Reservation = {
    name: '',
    email: '',
    numberOfPeople: 1,
    startTime: '',
    tableId: 1
  }

  constructor(private reservationService: ReservationService) {}

  submitReservation() {

    this.reservationService
      .createReservation(this.reservation)
      .subscribe({
        next: () => alert('Reservation successful'),
        error: err => alert(err.error.error)
      })

  }

}