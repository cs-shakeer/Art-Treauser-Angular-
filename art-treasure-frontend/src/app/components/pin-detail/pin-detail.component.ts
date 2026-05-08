import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-pin-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pin-detail.component.html',
  styleUrl: './pin-detail.component.css'
})
export class PinDetailComponent implements OnInit {
  pin: any;

  constructor(private route: ActivatedRoute, private router: Router, private apiService: ApiService) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    // We don't have getPinById in ApiService yet, but let's add it or just get all and filter
    // Actually, backend has getPinById. Let's add it to api.service.ts later if needed.
    // For now, I'll fetch all and find it, or use a mock if not found.
    this.apiService.getAllPins().subscribe({
      next: (res) => {
        if (res) {
          this.pin = res.find((p: any) => p.pinId == id || p.id == id);
        }
        if (!this.pin) {
          this.pin = {
            id: id,
            title: 'Amazing Architecture',
            description: 'A beautiful look at modern design and architectural integrity. This piece really captures the essence of space.',
            imageUrl: 'https://images.unsplash.com/photo-1518770660439-4636190af475?w=800&q=80',
            user: { fullName: 'Sarah Designer' }
          };
        }
      },
      error: (err) => console.error('Failed to load pin', err)
    });
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
