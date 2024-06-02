import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Portfolio } from '../../interfaces/portfolio';
import { ImpersonationService } from '../../services/impersonation.service';
import { PortfolioService } from '../../services/portfolio.service';

@Component({
  selector: 'app-portfolios',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './portfolios.component.html',
  styleUrl: './portfolios.component.css'
})
export class PortfoliosComponent implements OnInit {

  portfolios = signal<Portfolio[]>([]);
  newPortfolioName: any;
  impersonatedUserId: number | null;
  selectedPortfolio = signal<Portfolio | null>(null);

  constructor(
    private portfolioService: PortfolioService,
    private impersonationService: ImpersonationService,
    private router: Router
  ) {
    this.impersonatedUserId = this.impersonationService.getImpersonatedUserId()
    if (this.impersonatedUserId == null) {
      alert("Impersonate a user!")
      this.router.navigate(['']);
    }
  }

  ngOnInit(): void {
    if (this.impersonatedUserId != null) {
      this.portfolioService.getPortfolios(this.impersonatedUserId).subscribe(
        portfolios => this.portfolios.set(portfolios)
      )
    }
  }

  selectPortfolio(p: Portfolio) {
    this.selectedPortfolio.set(p);
  }

  addPortfolio(): void {
    if (this.newPortfolioName.trim()) {
      const newPortfolio: Portfolio = {
        id: null,
        name: this.newPortfolioName,
        userId: this.impersonationService.getImpersonatedUserId() || 0
      };
      this.portfolioService.createPortfolio(newPortfolio).subscribe(portfolio => {
        this.portfolios.update(value => [...value, portfolio]);
        this.newPortfolioName = '';
      });
    }
  }

  isSelectedPortfolio(portfolio: Portfolio): boolean {
    return this.selectedPortfolio()?.id === portfolio.id;
  }

}
