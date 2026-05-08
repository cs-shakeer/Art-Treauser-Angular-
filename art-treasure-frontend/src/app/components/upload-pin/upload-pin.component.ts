import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-upload-pin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './upload-pin.component.html',
  styleUrl: './upload-pin.component.css'
})
export class UploadPinComponent implements OnInit {
  pin = {
    title: '',
    description: '',
    imageUrl: '',
    boardId: null,
    user: { userId: null }
  };
  boards: any[] = [];
  errorMessage = '';

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit() {
    const userStr = localStorage.getItem('currentUser');
    if (userStr) {
      const user = JSON.parse(userStr);
      this.pin.user.userId = user.userId;
      
      this.apiService.getUserBoards(user.userId).subscribe({
        next: (res) => this.boards = res,
        error: (err) => console.error('Failed to load boards', err)
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  onUpload() {
    this.errorMessage = '';
    
    // Validate required fields
    if (!this.pin.imageUrl || !this.pin.title) {
       this.errorMessage = 'Image URL and Title are required.';
       return;
    }

    this.apiService.createPin(this.pin).subscribe({
      next: (res) => {
        alert(res); // Show success message
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Failed to upload pin', err);
        this.errorMessage = 'Failed to save pin. Please try again.';
      }
    });
  }
}
