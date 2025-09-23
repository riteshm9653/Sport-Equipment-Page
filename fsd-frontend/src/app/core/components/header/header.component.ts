import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  isLoggedIn = false;
  headerState = '*';

  ngOnInit(): void {
    this.isLoggedIn = true;
  }

  logout(): void {
    alert('Logging out will redirect you to the authentication service.');
    if (!confirm('Do you want to proceed?')) {
      return;
    }
    window.location.href = 'http://localhost:4202';
  }

  ngOnDestroy(): void {
    // No cleanup needed
  }
}
