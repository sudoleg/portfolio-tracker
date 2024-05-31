import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { User } from '../../interfaces/user';
import { ImpersonationService } from '../../services/impersonation.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  users: User[] = [];
  successMessage = signal("");

  constructor(private userService: UserService, private impersonationService: ImpersonationService) {
    userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

  impersonateUser(userId: number) {
    this.impersonationService.setImpersonatedUserId(userId);
    this.userService.getUser(userId).subscribe(result => {
      this.successMessage.set(`You are now 'logged in' as ${result.name} ${result.surname}!`)
    })
  }

}
