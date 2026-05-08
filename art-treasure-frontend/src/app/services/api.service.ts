import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/art/api';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any> {
    // We will use the UserController for now or a custom endpoint
    return this.http.get(`${this.baseUrl}/users`); // Assuming this exists or falls back
  }

  // Boards
  getUserBoards(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/boards/user/${userId}`);
  }

  createBoard(boardData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/boards/add`, boardData, { responseType: 'text' });
  }

  // Pins
  getAllPins(): Observable<any> {
    return this.http.get(`${this.baseUrl}/pins`);
  }

  getUserPins(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/pins/user/${userId}`);
  }

  createPin(pinData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/pins/create`, pinData, { responseType: 'text' });
  }

  updatePin(pinData: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/pins/update`, pinData);
  }
}
