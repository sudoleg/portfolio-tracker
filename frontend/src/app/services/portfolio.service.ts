import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservedValueOf } from 'rxjs';
import { Portfolio } from '../interfaces/portfolio';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  private apiUrl = "http://localhost:8080/api/v1/portfolios"

  constructor(private http: HttpClient) { }

  createPortfolio(portfolio: Portfolio): Observable<Portfolio> {
    return this.http.post<Portfolio>(this.apiUrl, portfolio)
  }

  getPortfolios(userId: number): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(
      `${this.apiUrl}?userId=${userId}`
    )
  }

  updatePortfolio(portfolioId: number, portfolio: Portfolio): Observable<Portfolio> {
    return this.http.patch<Portfolio>(
      `${this.apiUrl}/${portfolioId}`, portfolio
    )
  }

  deletePortfolio(portfolioId: number) {
    return this.http.delete(
      `${this.apiUrl}/${portfolioId}`
    )
  }

}
