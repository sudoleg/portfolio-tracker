import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { Position } from '../../interfaces/position';
import { PositionService } from '../../services/position.service';
import { SecurityService } from '../../services/security.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-positions',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './positions.component.html',
  styleUrls: ['./positions.component.css']
})
export class PositionsComponent implements OnInit {

  route: ActivatedRoute = inject(ActivatedRoute);
  positions: Position[] = [];
  financialInstrumentNames: Map<number, string> = new Map();
  portfolioId = 0;

  constructor(
    private positionService: PositionService,
    private securityService: SecurityService
  ) {
    this.portfolioId = Number(this.route.snapshot.params['id']);
  }

  ngOnInit(): void {
    this.positionService.getPositions(this.portfolioId).subscribe(positions => {
      this.positions = positions;
      const nameRequests = positions.map(position =>
        this.securityService.getSecurity(position.financialInstrumentId).pipe(
          map(security => {
            this.financialInstrumentNames.set(position.financialInstrumentId, security.name);
          })
        )
      );

      forkJoin(nameRequests).subscribe();
    });
    console.log(`Selected portfolio ID: ${this.portfolioId}`)
  }

  getSecurityName(id: number): string {
    return this.financialInstrumentNames.get(id) || 'Loading...';
  }

  getTotalValue(position: Position): number {
    return position.quantity * position.purchasePrice;
  }
}