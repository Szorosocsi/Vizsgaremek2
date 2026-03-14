import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent {

  @Input()
  tables: any[] = [];

  slotClick(table: string, time: string){
  console.log("Reservation:", table, time)
}
}