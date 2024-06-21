import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Security } from '../interfaces/security';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private apiUrl = `${environment.apiUrl}/api/v1/securities`

  constructor(private http: HttpClient) { }

  getSecurities(): Observable<Security[]> {
    return this.http.get<Security[]>(this.apiUrl);
  }

  getSecurity(id: number): Observable<Security> {
    return this.http.get<Security>(`${this.apiUrl}/${id}`);
  }

}
