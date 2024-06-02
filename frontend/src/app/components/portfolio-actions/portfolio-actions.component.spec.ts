import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioActionsComponent } from './portfolio-actions.component';

describe('PortfolioActionsComponent', () => {
  let component: PortfolioActionsComponent;
  let fixture: ComponentFixture<PortfolioActionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PortfolioActionsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PortfolioActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
