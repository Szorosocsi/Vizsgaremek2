import { Component } from '@angular/core';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {

tables = [

  {
    name: "Table 1",
    slots: ["18:00","20:00","22:00"]
  },

  {
    name: "Table 2",
    slots: ["18:00","20:00","22:00"]
  },

  {
    name: "Table 3",
    slots: ["18:00","20:00","22:00"]
  }

];

}