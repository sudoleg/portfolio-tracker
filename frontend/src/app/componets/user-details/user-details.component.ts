import { Component } from '@angular/core';
import { User } from '../../interfaces/user';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css'
})
export class UserDetailsComponent {

  user: User = {
    id: 1,
    username: "tonys",
    firstName: "Tony",
    lastName: "Stark",
    email: "tony.stark@marvel.com"
  }

}
