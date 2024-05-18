import { Routes } from '@angular/router';
import { UsersComponent } from './components/users/users.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';

export const routes: Routes = [
    { path: 'users', component: UsersComponent, title: "Users list" },
    { path: 'profile', component: UserDetailsComponent, title: "Profile" }
];
