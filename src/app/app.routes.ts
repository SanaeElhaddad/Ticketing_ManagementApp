import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ClientGuard, ManagerGuard } from './user.guard';
import { ProfilComponent } from './Client/profil/profil.component';
import { ListUserComponent } from './manager/list-user/list-user.component';

export const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'profile', component: ProfilComponent},
    {path: 'users', component: ListUserComponent},
    {path: '**', component: LoginComponent},
    {path: '', redirectTo: '/login', pathMatch: 'full'}
];
