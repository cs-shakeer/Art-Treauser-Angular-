import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  
  pins: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getAllPins().subscribe({
      next: (res) => {
        if (res && res.length > 0) {
          this.pins = res;
        } else {
          // Keep a few mocks just in case DB is completely empty for presentation
          this.pins = [
            { id: 1, title: 'Mountain Sunset', imageUrl: 'https://images.unsplash.com/photo-1506744626753-eda814117714?w=400&q=80' },
            { id: 2, title: 'Cyberpunk City', imageUrl: 'https://images.unsplash.com/photo-1518770660439-4636190af475?w=400&q=80' }
          ];
        }
      },
      error: (err) => {
        console.error('Failed to load pins', err);
        this.pins = [
          { id: 1, title: 'Mountain Sunset', imageUrl: 'https://images.unsplash.com/photo-1506744626753-eda814117714?w=400&q=80' }
        ];
      }
    });
  }
}
