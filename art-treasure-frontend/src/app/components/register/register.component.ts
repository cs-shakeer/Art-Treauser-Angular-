import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  user = {
    fullName: '',
    userName: '',
    email: '',
    mobile: '',
    password: '',
    profileImg: 'default.jpg' // Providing a default image name to satisfy backend
  };
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    this.errorMessage = '';
    this.authService.register(this.user).subscribe({
      next: (res: any) => {
        // Since backend returns plain text right now for register success ("John registered successfully")
        console.log('Registration success', res);
        this.router.navigate(['/login']);
      },
      error: (err: any) => {
        console.error('Registration failed', err);
        this.errorMessage = err.error || 'Registration failed. Please try again.';
      }
    });
  }
}
