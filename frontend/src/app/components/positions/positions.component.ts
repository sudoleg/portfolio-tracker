import { CommonModule } from '@angular/common';
import { Component, inject, model, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { Position } from '../../interfaces/position';
import { PositionService } from '../../services/position.service';
import { SecurityService } from '../../services/security.service';
import { toObservable } from '@angular/core/rxjs-interop'

@Component({
  selector: 'app-positions',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './positions.component.html',
  styleUrls: ['./positions.component.css']
})
export class PositionsComponent {

  // inject() allows to inject dependencies directly into class properties
  route: ActivatedRoute = inject(ActivatedRoute);
  positions = signal<Position[]>([]);
  financialInstrumentNames: Map<number, string> = new Map();
  portfolioId = model<number>(0);

  constructor(
    private positionService: PositionService,
    private securityService: SecurityService,
  ) {
    //this.portfolioId = Number(this.route.snapshot.params['id']);
    toObservable(this.portfolioId).subscribe(id => {
      this.loadPositionsInPortfolio(id);
    })
  }

  loadPositionsInPortfolio(portfolioId: number) {
    this.positionService.getPositions(this.portfolioId()).subscribe(positions => {
      this.positions.set(positions);
      const nameRequests = positions.map(position => this.securityService.getSecurity(position.financialInstrumentId).pipe(
        map(security => {
          this.financialInstrumentNames.set(position.financialInstrumentId, security.name);
        })
      )
      );
      forkJoin(nameRequests).subscribe();
    });
  }

  getSecurityName(id: number): string {
    return this.financialInstrumentNames.get(id) || 'Loading...';
  }

  getTotalValue(position: Position): number {
    return position.quantity * position.purchasePrice;
  }

  deletePosition(position: Position): void {
    if (position.id) {
      this.positionService.deletePosition(position.id).subscribe(() => {
        const updatedPositions = this.positions().filter(p => p.id !== position.id);
        this.positions.set(updatedPositions);
      });
    }
  }

}