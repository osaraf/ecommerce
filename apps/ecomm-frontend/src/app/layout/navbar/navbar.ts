import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'ecomm-navbar',
  imports: [CommonModule, FaIconComponent],
  standalone: true,
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class NavbarComponent {}
