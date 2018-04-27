import {Routes, RouterModule} from '@angular/router';
import { AuthGuard } from './_guards/auth.guard';
import { HomeComponent } from './home/home.component';
import { CreateComponent } from './create/create.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const appRoutes: Routes = [
    {path: '', component: HomeComponent, canActivate: [AuthGuard]},
    {path: 'register', component: RegisterComponent },
    {path: 'login', component: LoginComponent},
    {path: 'create', component: CreateComponent, canActivate: [AuthGuard]},
    {path: '**', redirectTo: ''}
];

export const routing = RouterModule.forRoot(appRoutes);