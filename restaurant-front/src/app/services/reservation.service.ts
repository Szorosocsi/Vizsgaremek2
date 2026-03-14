import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Reservation } from '../models/reservation'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private api = 'http://localhost:8080/reservations'

  constructor(private http: HttpClient) {}

  createReservation(reservation: Reservation): Observable<any> {
    return this.http.post(this.api, reservation)
  }

  getReservations(): Observable<any> {
    return this.http.get(this.api)
  }

}