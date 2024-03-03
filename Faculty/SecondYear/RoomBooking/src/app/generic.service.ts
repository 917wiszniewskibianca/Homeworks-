import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Room} from './rooms';
import {Reservations} from "./reservations";

@Injectable({
  providedIn: 'root'
})
export class GenericService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  private backendUrl = 'http://localhost:8888/HotelBooking/';

  constructor(private http: HttpClient) {
  }

  fetchRooms(category: string, type: string, price: number, hotel: string): Observable<Room[]> {
    return this.http.get<Room[]>(this.backendUrl + `Browse/browse-room.php&category=${category}&type=${type}?price=${price}&hotel=${hotel}`)
      .pipe(catchError(this.handleError<Room[]>('fetchRooms', []))
      );
  }

  deleteRoom(roomId: number): Observable<any> {
    return this.http.post(this.backendUrl + `Remove/remove-room.php`, {id: roomId});
  }


  addRoom(category: string, type: string, price: number, hotel: string): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = {

      category: category,
      type: type,
      price: price,
      hotel: hotel,

    };

    return this.http.post(this.backendUrl + 'Add/add-room.php', JSON.stringify(body), { headers: headers });
  }


  updateRoom(id:number, category: string, type: string, price: number, hotel: string): Observable<any> {
      return this.http.post(this.backendUrl + `Update/update-room.php`, {
        id: id,
        category: category,
        type: type,
        price: price,
        hotel: hotel
      });
    }

  private handleError<T>(operation = 'operation', result?: T): (error: any) => Observable<T> {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
