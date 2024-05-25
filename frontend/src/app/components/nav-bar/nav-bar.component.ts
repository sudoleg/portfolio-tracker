import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ImpersonationService } from '../../services/impersonation.service';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

  constructor(private impersonationService: ImpersonationService) { }

  isUserImpersonated(): boolean {
    if (this.impersonationService.getImpersonatedUserId() == null) {
      return false
    }
    return true
  }

}
