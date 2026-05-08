import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-board-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './board-list.component.html',
  styleUrl: './board-list.component.css'
})
export class BoardListComponent implements OnInit {
  boards: any[] = [];
  showCreateForm = false;
  newBoard = { name: '', description: '', user: { userId: null } };
  currentUser: any;

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit() {
    const userStr = localStorage.getItem('currentUser');
    if (userStr) {
      this.currentUser = JSON.parse(userStr);
      this.newBoard.user.userId = this.currentUser.userId;
      this.loadBoards();
    } else {
      this.router.navigate(['/login']);
    }
  }

  loadBoards() {
    this.apiService.getUserBoards(this.currentUser.userId).subscribe({
      next: (res) => this.boards = res,
      error: (err) => console.error('Failed to load boards', err)
    });
  }

  onCreateBoard() {
    this.apiService.createBoard(this.newBoard).subscribe({
      next: (res) => {
        alert(res);
        this.loadBoards();
        this.newBoard = { name: '', description: '', user: { userId: this.currentUser.userId } };
        this.showCreateForm = false;
      },
      error: (err) => console.error('Failed to create board', err)
    });
  }
}
