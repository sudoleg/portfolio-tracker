import { Component } from '@angular/core';
import { Portfolio } from '../../interfaces/portfolio';
import { PortfolioService } from '../../services/portfolio.service';
import { ImpersonationService } from '../../services/impersonation.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-portfolios',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './portfolios.component.html',
  styleUrl: './portfolios.component.css'
})
export class PortfoliosComponent {

  portfolios: Portfolio[] = []
  selectedPortfolio: Portfolio | null = null

  constructor(
    private portfolioService: PortfolioService,
    private impersonationService: ImpersonationService
  ) {
    this.loadUsersPortfolios()
  }

  loadUsersPortfolios(): void {
    let userId = this.impersonationService.getImpersonatedUserId()
    if (userId != null) {
      this.portfolioService.getPortfolios(userId).subscribe(
        portfolios => this.portfolios = portfolios
      )
    }
  }

}
