import { Component } from '@angular/core';
import { Portfolio } from '../../interfaces/portfolio';
import { PortfolioService } from '../../services/portfolio.service';
import { ImpersonationService } from '../../services/impersonation.service';
import { CommonModule, NgFor } from '@angular/common';

@Component({
  selector: 'app-portfolios',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './portfolios.component.html',
  styleUrl: './portfolios.component.css'
})
export class PortfoliosComponent {

  portfolios: Portfolio[] = []
  selectedPortfolio: Portfolio | null = null

  constructor(private portfolioService: PortfolioService, private impersonationService: ImpersonationService) {
    this.loadUsersPortfolios()
  }

  loadUsersPortfolios(): void {
    let userId = this.impersonationService.getImpersonatedUserId()
    if (userId != null) {
      this.portfolioService.getPortfolios(userId).subscribe(
        portfolios => this.portfolios = portfolios
      )
    } else {
      throw new Error("Impersonate a user to fetch portfolios!")
    }
  }

  selectPortfolio(_t3: any) {
    throw new Error('Method not implemented.');
  }

}
