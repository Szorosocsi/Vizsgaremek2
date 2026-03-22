import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  // Reservation form state
  reservationDate = signal('');
  reservationTime = signal('');
  guestCount = signal('2');
  guestName = signal('');
  guestEmail = signal('');

  // Get today's date in YYYY-MM-DD format
  getTodayDate(): string {
    const today = new Date();
    return today.toISOString().split('T')[0];
  }

  // Get minimum time (current time or 09:00 if before opening)
  getMinTime(): string {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    return `${hours}:${minutes}`;
  }

  handleReservation(event: Event): void {
    event.preventDefault();
    console.log('Reservation submitted:', {
      date: this.reservationDate(),
      time: this.reservationTime(),
      guests: this.guestCount(),
      name: this.guestName(),
      email: this.guestEmail()
    });
    // TODO: Send to backend when ready
    alert('Reservation request received! We will confirm shortly.');
    this.resetForm();
  }

  resetForm(): void {
    this.reservationDate.set('');
    this.reservationTime.set('');
    this.guestCount.set('2');
    this.guestName.set('');
    this.guestEmail.set('');
  }
}
