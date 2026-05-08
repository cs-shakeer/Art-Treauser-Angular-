import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BoardListComponent } from './components/board-list/board-list.component';
import { UploadPinComponent } from './components/upload-pin/upload-pin.component';
import { PinDetailComponent } from './components/pin-detail/pin-detail.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'boards', component: BoardListComponent },
  { path: 'upload', component: UploadPinComponent },
  { path: 'pin/:id', component: PinDetailComponent },
  { path: '**', redirectTo: '' }
];
