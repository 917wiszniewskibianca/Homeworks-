import { Component } from '@angular/core';

@Component({
  selector: '',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent {
  navigateTo(url: string): void {
      window.location.href = url;
    }
  }

