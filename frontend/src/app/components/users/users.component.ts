import { Component, OnInit, inject } from '@angular/core';
import { User } from '../../interfaces/user';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  users: User[] = []
  userService: UserService = inject(UserService)

  constructor() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

}
