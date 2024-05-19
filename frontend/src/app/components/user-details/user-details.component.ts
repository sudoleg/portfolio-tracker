import { ChangeDetectorRef, Component } from '@angular/core';
import { User } from '../../interfaces/user';
import { CommonModule } from '@angular/common';
import { ImpersonationService } from '../../services/impersonation.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css'
})
export class UserDetailsComponent {

  impersonatedUser: User | null = null
  impersonatedUserId: number | null = null

  constructor(
    private impersonationService: ImpersonationService,
    private userService: UserService,
    private cd: ChangeDetectorRef
  ) {
    this.impersonatedUserId = impersonationService.getImpersonatedUserId()
    if (this.impersonatedUserId != null) {
      userService.getUser(this.impersonatedUserId).subscribe(
        user => this.impersonatedUser = user)
    }
  }

  clearImpersonation(): void {
    this.impersonationService.clearImpersonatedUserId();
    this.impersonatedUser = null;
    this.cd.detectChanges(); // Trigger change detection
  }

}
