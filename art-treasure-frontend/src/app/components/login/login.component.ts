import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.errorMessage = '';
    const payload = {
      userName: this.username,
      password: this.password
    };

    this.authService.login(payload).subscribe({
      next: (res: any) => {
        console.log('Login success', res);
        // Store user info in localStorage
        localStorage.setItem('currentUser', JSON.stringify(res));
        this.router.navigate(['/']);
      },
      error: (err: any) => {
        console.error('Login failed', err);
        this.errorMessage = 'Invalid username or password.';
      }
    });
  }
}
