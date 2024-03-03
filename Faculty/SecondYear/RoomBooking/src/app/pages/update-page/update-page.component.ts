import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Room {
  id: string;
  category: string;
  type: string;
  price: string;
  hotel: string;
}

@Component({
  selector: 'app-update-page',
  templateUrl: './update-page.component.html',
  styleUrls: ['./update-page.component.css']
})
export class UpdatePageComponent implements OnInit {
  rooms: Room[] = [];
  roomId!: string;
  category!: string;
  type!: string;
  price!: string;
  hotel!: string;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.populateTable();
  }

  populateTable(): void {
    this.http.get<Room[]>('populateTable.php').subscribe(
      (result) => {
        this.rooms = result;
      },
      (error) => {
        console.log(error);
        alert('An error occurred while populating the table.');
      }
    );
  }

  updateRoom(): void {
    if (this.category && this.type && this.hotel && this.price) {
      this.http.post('update-room.php', {
        id: this.roomId,
        category: this.category,
        type: this.type,
        hotel: this.hotel,
        price: this.price
      }).subscribe(
        (result) => {
          console.log(result);
          alert(result);
          window.location.href = 'update-page.component.html';
        },
        (error) => {
          console.log(error);
          alert('An error occurred while updating the room.');
        }
      );
    } else {
      alert('Complete the required fields!');
    }
  }

  selectRoom(room: Room): void {
    this.roomId = room.id;
    this.category = room.category;
    this.type = room.type;
    this.price = room.price;
    this.hotel = room.hotel;
  }
}
