import { Routes } from '@angular/router';
import { UsersComponent } from './components/users/users.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { PortfoliosComponent } from './components/portfolios/portfolios.component';
import { SecuritiesComponent } from './components/securities/securities.component';
import { PositionsComponent } from './components/positions/positions.component';
import { SecurityDetailsComponent } from './components/security-details/security-details.component';
import { PortfolioActionsComponent } from './components/portfolio-actions/portfolio-actions.component';

export const routes: Routes = [
    { path: '', redirectTo: 'users', pathMatch: 'full' },
    {
        path: 'users',
        children: [
            {
                path: '',
                component: UsersComponent,
                outlet: 'primary'
            }
        ],
        title: "Users"
    },
    {
        path: 'profile',
        children: [
            {
                path: '',
                component: UserDetailsComponent,
                outlet: 'primary'
            }
        ],
        title: "Profile"
    },
    {
        path: 'portfolios',
        children: [
            {
                path: '',
                component: PortfoliosComponent,
                outlet: "secondary"
            },
            {
                path: ':portfolioId',
                component: PositionsComponent,
                outlet: 'primary',
            }
        ],
        title: "Portfolios"
    },
    {
        path: 'portfolios-edit',
        component: PortfolioActionsComponent,
        outlet: 'primary'
    },
    {
        path: 'securities',
        children: [
            {
                path: '',
                component: SecuritiesComponent,
                outlet: "primary"
            }, {
                path: ':id',
                component: SecurityDetailsComponent,
                outlet: 'primary'
            }
        ],
        title: "Securities"
    }
];
