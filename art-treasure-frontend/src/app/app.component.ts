import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'art-treasure-frontend';
  currentUser: any = null;

  constructor(private router: Router) {}

  ngOnInit() {
    this.checkUser();
    // Listen to route changes to update navbar state
    this.router.events.subscribe(() => {
      this.checkUser();
    });
  }

  checkUser() {
    const user = localStorage.getItem('currentUser');
    if (user) {
      this.currentUser = JSON.parse(user);
    } else {
      this.currentUser = null;
    }
  }

  isAdmin() {
    // Hardcoded admin email or use isProfessional flag. Let's use a specific email for segregation.
    return this.currentUser && this.currentUser.email === 'admin@art.com';
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUser = null;
    this.router.navigate(['/login']);
  }
}
