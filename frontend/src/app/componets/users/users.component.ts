import { Component } from '@angular/core';
import { User } from '../../interfaces/user';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  user: User = {
    id: 1,
    username: "tonys",
    firstName: "Tony",
    lastName: "Stark",
    email: "tony.stark@marvel.com"
  }

}
