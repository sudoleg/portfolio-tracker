import { Component } from '@angular/core';
import { Security } from '../../interfaces/security';
import { SecurityService } from '../../services/security.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-securities',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './securities.component.html',
  styleUrl: './securities.component.css'
})
export class SecuritiesComponent {

  securities: Security[] | null = []

  constructor(private securityService: SecurityService) {
    this.securityService.getSecurities().subscribe(result => this.securities = result)
  }

}
