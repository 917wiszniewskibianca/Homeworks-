import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-reservations-page',
  templateUrl: './reservations-page.component.html',
  styleUrls: ['./reservations-page.component.css']
})
export class ReservationsPageComponent {
  addFirstName!: string ;
  addLastName!: string ;
  addRoomID!: number ;
  addStartDate!: Date;
  addEndDate!: Date ;
  addCategory!: string ;
  addType!: string ;
  addPrice!: number;
  addHotel!: string ;
  notFreeIntervals: string[] = [];
  reservationId: number = 0;
  myReservations: Reservation[] = [];

  constructor(private http: HttpClient) {}

  showUnavailableIntervals() {
    const roomId = this.addRoomID;
    const startDate = this.formatDate(this.addStartDate);
    const endDate = this.formatDate(this.addEndDate);

    const params = new HttpParams()
      .set('roomId', roomId.toString())
      .set('startDate', startDate)
      .set('endDate', endDate);


    this.http.get<string[]>('php/showIntervals.php', { params })
      .subscribe(result => {
        this.notFreeIntervals = result;
      });
  }

  addReservation() {
    const firstName = this.addFirstName;
    const lastName = this.addLastName;
    const roomId = this.addRoomID;
    const startDate = this.formatDate(this.addStartDate);
    const endDate = this.formatDate(this.addEndDate);

    if (firstName && lastName && startDate && endDate && roomId) {
      const reservationData = {
        firstName,
        lastName,
        roomId,
        startDate,
        endDate
      };

      this.http.post<any>('php/addReservation.php', reservationData)
        .subscribe(result => {
          window.location.href = 'reservations.html';
          alert(result);
        });
    } else {
      alert('Complete the required fields!');
    }
  }

  findReservations() {
    const reservationId = this.reservationId;
    const roomId = 0;
    const category = '';
    const type = '';
    const price = 0;
    const hotel = '';
    const startDate = new Date();
    const endDate = new Date();

    const params = new HttpParams()
      .set('id', reservationId.toString())
      .set('roomId', roomId.toString())
      .set('category', category)
      .set('type', type)
      .set('price', price.toString())
      .set('hotel', hotel)
      .set('startDate', this.formatDate(startDate))
      .set('endDate', this.formatDate(endDate));

    this.http.get<Reservation[]>('php/populateTable.php', { params })
      .subscribe(result => {
        this.myReservations = result;
      });
  }

  cancelReservation() {
    const reservationId = this.reservationId;

    if (reservationId) {
      this.http.post<any>('php/cancelReservation.php', { id: reservationId })
        .subscribe(result => {
          window.location.href = 'reservations.html';
          alert(result);
        });
    } else {
      alert('Complete the required fields!');
    }
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  GetFirstName(value: string) {
    this.addFirstName=value;
  }

  GetCategory(value: string) {
     this.addCategory=value;
  }

  GetRoomId(value: string) {
      this.addRoomID=Number(value);
  }

  GetLastName(value: string) {
      this.addLastName=value;
  }

  GetType(value: string) {
    this.addType=value;
  }

  GetPrice(value: string) {
    this.addPrice=Number(value);
  }

  GetHotel(value: string) {
   this.addHotel=value;
  }

  GetStartDate(value: string) {
     this.addStartDate= new Date(value);
  }

  GetEndDate(value: string) {
      this.addEndDate= new Date(value);
  }

  GetReservationId(value: any) {
    this.reservationId =value;

  }
}

interface Reservation {
  id: number;
  roomId: number;
  category: string;
  type: string;
  price: number;
  hotel: string;
  startDate: Date;
  endDate: Date;
}
