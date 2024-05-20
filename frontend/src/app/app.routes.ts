import { Routes } from '@angular/router';
import { UsersComponent } from './components/users/users.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { PortfoliosComponent } from './components/portfolios/portfolios.component';
import { SecuritiesComponent } from './components/securities/securities.component';

export const routes: Routes = [
    { path: 'users', component: UsersComponent, title: "Users" },
    { path: 'profile', component: UserDetailsComponent, title: "Profile" },
    { path: 'portfolios', component: PortfoliosComponent, title: "Portfolios" },
    { path: 'securities', component: SecuritiesComponent, title: "Securities" }
];
