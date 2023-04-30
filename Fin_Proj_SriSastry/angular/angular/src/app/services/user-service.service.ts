import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private apiUrl = 'http://localhost:8080/user';
  constructor(private httpClient: HttpClient) { }
  getAllUsers(): Observable<any[]> {
    return this.httpClient.get<any[]>(this.apiUrl + "/all");
  }

  getUserById(userId: string): Observable<User> {
    const url = `${this.apiUrl}/${userId}`;
    return this.httpClient.get<User>(url);
  }

  saveUser(data: any) {
    const url = `${this.apiUrl}/save`;
    return this.httpClient.post(url, data);
  }

  updateUser(id: string, userData: any): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.put(`${this.apiUrl}/${id}`, JSON.stringify(userData), { headers });
  }

  deleteUser(id: string): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.httpClient.delete<void>(url);
  }

}
