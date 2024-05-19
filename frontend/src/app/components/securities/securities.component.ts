import { Component } from '@angular/core';
import { Security } from '../../interfaces/security';
import { SecurityService } from '../../services/security.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-securities',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './securities.component.html',
  styleUrl: './securities.component.css'
})
export class SecuritiesComponent {

  securities: Security[] | null = []

  constructor(private securityService: SecurityService) {
    this.securityService.getSecurities().subscribe(result => this.securities = result)
  }

  addToPortfolio(_t3: any) {
    throw new Error('Method not implemented.');
  }

}
