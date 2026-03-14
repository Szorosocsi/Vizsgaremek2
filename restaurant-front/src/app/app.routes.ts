import { Routes } from '@angular/router';

import { BookingComponent } from './pages/booking/booking.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },

    { path: 'home', component: HomeComponent },
    { path: 'booking', component: BookingComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent }
];