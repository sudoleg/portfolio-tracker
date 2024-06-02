import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Portfolio } from '../../interfaces/portfolio';
import { ImpersonationService } from '../../services/impersonation.service';
import { PortfolioService } from '../../services/portfolio.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-portfolio-actions',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './portfolio-actions.component.html',
  styleUrl: './portfolio-actions.component.css'
})
export class PortfolioActionsComponent implements OnInit {

  selectedPortfolioId: number | null = null;
  updatedPortfolioName = "";
  portfolios: Portfolio[] = [];
  impersonatedUserId: number | null = null;

  constructor(
    private portfolioService: PortfolioService,
    private impersonationService: ImpersonationService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.impersonatedUserId = this.impersonationService.getImpersonatedUserId()
    if (this.impersonatedUserId) {
      this.portfolioService.getPortfolios(this.impersonatedUserId).subscribe(result => {
        this.portfolios = result;
      })
    }
  }

  updatePortfolio(portfolioId: number) {
    if (portfolioId && this.updatedPortfolioName && this.impersonatedUserId) {
      let updatedPortfolio: Portfolio = {
        id: null,
        name: this.updatedPortfolioName,
        userId: this.impersonatedUserId
      }
      this.portfolioService.updatePortfolio(portfolioId, updatedPortfolio).subscribe(() => {
        this.redirectAndNavigateTo(`/portfolios/${portfolioId}`);
      });
    }
  }

  deletePortfolio(portfolioId: number) {
    this.portfolioService.deletePortfolio(portfolioId).subscribe(() => {
      this.redirectAndNavigateTo('/portfolios');
    });
  }

  private redirectAndNavigateTo(path: string) {
    this.router.navigate([path], { replaceUrl: true }).then(() => {
      window.location.href = path;
    });
  }

}
