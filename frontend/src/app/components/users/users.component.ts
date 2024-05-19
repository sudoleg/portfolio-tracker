import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { User } from '../../interfaces/user';
import { UserService } from '../../services/user.service';
import { ImpersonationService } from '../../services/impersonation.service';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  users: User[] = []

  constructor(private userService: UserService, private impersonationService: ImpersonationService) {
    userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

  impersonateUser(userId: number) {
    this.impersonationService.setImpersonatedUserId(userId)
  }

}
