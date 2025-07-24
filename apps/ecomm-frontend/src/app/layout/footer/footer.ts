// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
//


import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'ecomm-footer',
  standalone:true,
  imports: [CommonModule, FaIconComponent],
  templateUrl: './footer.html',
  styleUrl: './footer.scss',
})
export class FooterComponent {


}

