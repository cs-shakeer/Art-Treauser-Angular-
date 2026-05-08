import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {
  users: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getAllUsers().subscribe({
      next: (res) => {
        if (res && res.length > 0) {
           this.users = res;
        }
      },
      error: (err) => console.error('Could not load users, using mock data', err)
    });
  }
}
