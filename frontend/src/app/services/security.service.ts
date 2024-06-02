import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Security } from '../interfaces/security';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private apiUrl = "http://localhost:8080/api/v1/securities"

  constructor(private http: HttpClient) { }

  getSecurities(): Observable<Security[]> {
    return this.http.get<Security[]>(this.apiUrl);
  }

  getSecurity(id: number): Observable<Security> {
    return this.http.get<Security>(`${this.apiUrl}/${id}`);
  }

}
