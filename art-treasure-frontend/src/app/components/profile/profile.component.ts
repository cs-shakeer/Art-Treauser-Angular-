import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  user: any = {};
  followersCount = 0;
  followingCount = 0;
  userPins: any[] = [];

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit(): void {
    const storedUser = localStorage.getItem('currentUser');
    if (storedUser) {
      this.user = JSON.parse(storedUser);
      this.loadUserPins();
    } else {
      this.router.navigate(['/login']);
    }
  }

  loadUserPins() {
    this.apiService.getUserPins(this.user.userId).subscribe({
      next: (res) => this.userPins = res,
      error: (err) => console.error('Failed to load user pins', err)
    });
  }
}
