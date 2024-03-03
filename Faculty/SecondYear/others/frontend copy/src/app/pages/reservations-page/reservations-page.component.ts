import { Component } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

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
  reservationId: number = 0;
  myReservations: Reservation[] = [];
  intervals: any[]=[]

  constructor(private http: HttpClient) {}

  showUnavailableIntervals() {
    const params = new HttpParams().set('roomId', this.addRoomID.toString());

    this.http.get<any[]>('http://localhost:8888/HotelBooking/Reservations/showIntervals.php', { params })
      .subscribe(
        (intervals) => {
          this.intervals = intervals;
        },
        (error) => {
          console.log(error);
          alert('An error occurred while fetching unavailable intervals.');
        }
      );
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

      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      };

      this.http.post<any>('http://localhost:8888/HotelBooking/Reservations/addReservation.php', reservationData, httpOptions)
        .subscribe(result => {
          if (result.success) {
            window.location.href = 'reservations.html';
            alert('Reservation added successfully!');
          } else {
            alert('Could not add a new reservation!\n' + result.message);
          }
        });
    } else {
      alert('Complete the required fields!');
    }
  }

  findReservations() {
    const reservationId = this.reservationId;

    if (reservationId) {
      this.http.get<Reservation[]>('http://localhost:8888/HotelBooking/Reservations/populateTable.php?id=' + reservationId)
        .subscribe(
          (reservations) => {
            this.myReservations = reservations;
          },
          (error) => {
            console.log(error);
            alert('An error occurred while populating reservations.');
          }
        );
    } else {
      alert('Please enter a reservation ID.');
    }
  }





  cancelReservation() {
    const reservationId = this.reservationId;

    if (reservationId) {
      const requestData = {
        id: reservationId
      };

      this.http.post<any>('http://localhost:8888/HotelBooking/Reservations/cancelReservation.php', requestData)
        .subscribe(result => {
          if (result.success) {
            console.log(result.message);
            alert(result.message)
          } else {
            console.log(result.message);
            alert('Could not cancel reservation!');
          }
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
    this.reservationId =Number(value);

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
