import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {GenericService} from "../../generic.service";
import {Router} from "@angular/router";
import {Room} from "../../rooms";

@Component({
  selector: 'app-browse-page',
  templateUrl: './browse-page.component.html',
  styleUrls: ['./browse-page.component.css']
})
export class BrowsePageComponent implements OnInit {
  category: string='';
  type: string='';
  price: number=0;
  hotel: string='';
  currentPage: number = 1;
  //rooms: Object = [];
  rooms : Room[] = [];
  columns: string[] = ["id", "category", "type", "price", "hotel"];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchData();
  }

  findRooms() {
    this.currentPage = 1;
    this.fetchData();
  }

  nextPage() {
    this.currentPage++;
    this.fetchData();
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.fetchData();
    }
  }

  fetchData() {
    const data = {
      category: this.category,
      type: this.type,
      price: this.price,
      hotel: this.hotel,
      pg: this.currentPage.toString()
    };

    this.http.get('http://localhost:8888/HotelBooking/Browse/browse-room.php', { params: data })
      .subscribe((result) => {
        // @ts-ignore
        this.rooms= result;
      });
  }

  GetCategory(value: string) {
    this.category=value
  }

  GetType(value: string) {
    this.type=value;
  }

  GetPrice(value: string) {
    this.price=Number(value);
  }

  GetHotel(value: string) {
    this.hotel=value;
  }
  GetPage(){
    return this.currentPage;
  }
}
