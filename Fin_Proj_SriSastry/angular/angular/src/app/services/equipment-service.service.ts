import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipmentServiceService {
  private apiUrl = 'http://localhost:8080/equipment';
  constructor(private httpClient: HttpClient) { }
  getAllEquipments(): Observable<any[]> {
    return this.httpClient.get<any[]>(this.apiUrl + "/all");
  }
  getEquipmentByUserId(userId: string): Observable<any> {
    return this.httpClient
      .get(`${this.apiUrl}/user/${userId}`)
      .pipe(
        catchError(error => {
          console.log('Error fetching equipment:', error);
          return of([]);
        })
      );
  }

  saveEquipment(data: any) {
    const url = `${this.apiUrl}/save`;
    return this.httpClient.post(url, data);
  }

  updateEquipment(id: string, equipmentData: any): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.put(`${this.apiUrl}/${id}`, JSON.stringify(equipmentData), { headers });
  }

  deleteEquipment(id: string): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.httpClient.delete<void>(url);
  }
}
