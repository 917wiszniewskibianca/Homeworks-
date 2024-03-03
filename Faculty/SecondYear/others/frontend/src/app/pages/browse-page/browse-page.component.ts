import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Room } from "../../rooms";

@Component({
  selector: 'app-browse-page',
  templateUrl: './browse-page.component.html',
  styleUrls: ['./browse-page.component.css']
})
export class BrowsePageComponent implements OnInit {
  category: string = '';
  type: string = '';
  price: number = 0;
  hotel: string = '';
  currentPage: number = 1;
  rooms: Room[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.findRooms();
  }

  findRooms(): void {
    let params = new HttpParams().set('pg', this.currentPage.toString());

    if (this.category) {
      params = params.set('category', this.category);
    }

    if (this.type) {
      params = params.set('type', this.type);
    }

    if (this.price) {
      params = params.set('price', this.price);
    }

    if (this.hotel) {
      params = params.set('hotel', this.hotel);
    }

    console.log(params);

    this.http
      .get<{ success: boolean; message: string; data: Room[] }>('http://localhost:8888/HotelBooking/Browse/browse-room.php', { params })
      .subscribe((result) => {
        this.rooms = result.data; // Assign the 'data' property of the response to the 'rooms' variable
      });
  }

  nextPage() {
    this.currentPage++;
    this.findRooms();
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.findRooms();
    }
  }

  GetCategory(value: string) {
    this.category = value;
  }

  GetType(value: string) {
    this.type = value;
  }

  GetPrice(value: string) {
    this.price = Number(value);
  }

  GetHotel(value: string) {
    this.hotel = value;
  }

  GetPage() {
    return this.currentPage;
  }
}
