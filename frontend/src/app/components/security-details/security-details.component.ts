import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Security } from '../../interfaces/security';
import { SecurityService } from '../../services/security.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { PositionService } from '../../services/position.service';
import { Position } from '../../interfaces/position';
import { Portfolio } from '../../interfaces/portfolio';
import { PortfolioService } from '../../services/portfolio.service';
import { ImpersonationService } from '../../services/impersonation.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-security-details',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './security-details.component.html',
  styleUrl: './security-details.component.css'
})
export class SecurityDetailsComponent implements OnInit {

  route: ActivatedRoute = inject(ActivatedRoute)
  securityId: number | null;
  security: Security | null = null;
  portfolios: Portfolio[] = [];
  quantity: number = 0; // Initialize quantity
  selectedPortfolioId: number | null = null; // Initialize selectedPortfolioId
  isUserLoggedIn: boolean = false;
  successMessage: string | null = null; // For success message
  successPortfolioId: number | null = null; // For storing the portfolio ID on success

  constructor(
    private securityService: SecurityService,
    private positionService: PositionService,
    private portfolioService: PortfolioService,
    private impersonationService: ImpersonationService) {
    this.securityId = Number(this.route.snapshot.params['id']);
  }

  ngOnInit(): void {
    if (this.securityId != null) {
      this.securityService.getSecurity(this.securityId).subscribe(result => {
        this.security = result;
      })
    }
    let userId = this.impersonationService.getImpersonatedUserId()
    if (userId) {
      this.isUserLoggedIn = true;
      this.portfolioService.getPortfolios(userId).subscribe(result => {
        this.portfolios = result;
      })
    }
  }

  addToPortfolio(portfolioId: number | null, quantity: number) {
    if (this.security && portfolioId != null && quantity > 0) {
      let p: Position = {
        portfolioId: portfolioId,
        quantity: quantity,
        financialInstrumentId: this.security.id,
        purchasePrice: 156.00
      }

      this.positionService.addPosition(p).subscribe(result => {
        this.successMessage = `Successfully added ${quantity} shares to your portfolio.`;
        this.successPortfolioId = result.portfolioId;
      }, error => {
        this.successMessage = `Failed to add ${quantity} shares to your portfolio.`;
        this.successPortfolioId = null;
      });
    }
  }

}
