import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Portfolio } from '../../interfaces/portfolio';
import { ImpersonationService } from '../../services/impersonation.service';
import { PortfolioService } from '../../services/portfolio.service';

@Component({
  selector: 'app-portfolio-actions',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './portfolio-actions.component.html',
  styleUrl: './portfolio-actions.component.css'
})
export class PortfolioActionsComponent implements OnInit {

  selectedPortfolioId: number | null = null;
  updatedPortfolioName = '';
  portfolios = signal<Portfolio[]>([]);
  impersonatedUserId: number | null = null;
  message = signal<string>("");

  constructor(
    private portfolioService: PortfolioService,
    private impersonationService: ImpersonationService,
  ) { }

  ngOnInit(): void {
    this.impersonatedUserId = this.impersonationService.getImpersonatedUserId();
    if (this.impersonatedUserId) {
      this.loadPortfolios();
    }
  }

  private loadPortfolios(): void {
    this.portfolioService.getPortfolios(this.impersonatedUserId!).subscribe(result => {
      this.portfolios.set(result);
    });
  }

  updatePortfolio(): void {
    if (this.selectedPortfolioId && this.updatedPortfolioName && this.impersonatedUserId) {
      const updatedPortfolio: Portfolio = {
        id: this.selectedPortfolioId,
        name: this.updatedPortfolioName,
        userId: this.impersonatedUserId
      };

      this.portfolioService.updatePortfolio(this.selectedPortfolioId, updatedPortfolio).subscribe(() => {
        this.loadPortfolios();
        this.updatedPortfolioName = '';
        this.message.set('Sucessfully updated portfolio name!');
      });
    }
  }

  deletePortfolio(): void {
    if (this.selectedPortfolioId) {
      this.portfolioService.deletePortfolio(this.selectedPortfolioId).subscribe(() => {
        this.loadPortfolios();
        this.selectedPortfolioId = null;
        this.message.set('Successfully deleted portfolio.');
      });
    }
  }

}