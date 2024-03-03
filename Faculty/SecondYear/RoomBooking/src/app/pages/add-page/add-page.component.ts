import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {GenericService} from "../../generic.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-page',
  templateUrl: './add-page.component.html',
  styleUrls: ['./add-page.component.css']
})
export class AddPageComponent implements OnInit {
  category!: string;
  type!: string;
  price!: number;
  hotel!: string;

  //constructor(private http: HttpClient) { }
  constructor(private service: GenericService, private router: Router) {
  }

  ngOnInit() {
  }

 /*onSubmit1() {
    if (this.category && this.type && this.price && this.hotel) {
      this.http.post('php/add-room.php', { category: this.category, type: this.type, price: this.price, hotel: this.hotel })
        .subscribe(result => {
          window.location.href = 'add-page.component.html';
          alert(result);
        });
    } else {
      alert('Complete the required fields!');
    }
  }*/
  onSubmit(): void {
    if (this.category && this.type && this.price && this.hotel) {
      this.service.addRoom(this.category, this.type, Number(this.price), this.hotel).subscribe((response:any) => {
        if (response.success) {
          // Room added successfully, show pop-up notification
          alert('Room added successfully!');
        } else {
          // Failed to add room, show error notification
          alert('Failed to add the room. Please try again.');
        }
      });
    }
    else  alert('Complete the required fields!');
  }


  GetCategory(value: string) {
    this.category= value;

  }

  GetType(value: string) {
    this.type=value;
  }

  GetPrice(value: string) {
    this.price= Number(value)
  }

  GetHotel(value: string) {
     this.hotel=value;
  }
}
