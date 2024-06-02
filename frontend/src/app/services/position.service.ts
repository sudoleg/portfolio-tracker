import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Position } from '../interfaces/position';

@Injectable({
  providedIn: 'root'
})
export class PositionService {

  private apiUrl = "http://localhost:8080/api/v1/positions"

  constructor(private http: HttpClient) { }

  addPosition(position: Position): Observable<Position> {
    return this.http.post<Position>(
      `${this.apiUrl}`, position
    );
  }

  getPositions(portfolioId: number): Observable<Position[]> {
    return this.http.get<Position[]>(
      `${this.apiUrl}?portfolioId=${portfolioId}`
    )
  }

  deletePosition(positionId: number) {
    console.log("deleting position " + positionId)
    return this.http.delete(
      `${this.apiUrl}/${positionId}`
    )
  }

}
