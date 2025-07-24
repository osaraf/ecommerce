import {Component, inject, OnInit} from '@angular/core';
import {RouterModule, RouterOutlet} from '@angular/router';
import {FaConfig, FaIconComponent, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {fontAwesomeIcons} from "./shared/font-awesome-icons";
import { faHome } from '@fortawesome/free-solid-svg-icons';
import {NavbarComponent} from "./layout/navbar/navbar";
// import { NavbarComponent } from './layout/navbar';
import {FooterComponent} from "./layout/footer/footer";
@Component({
  standalone:true,

  imports: [FaIconComponent, RouterOutlet,NavbarComponent,FooterComponent],
  selector: 'ecomm-root',
  templateUrl: './app.html',
  styleUrls: ['./app.scss']
})
export class App implements OnInit{
  private faIconLibrary= inject(FaIconLibrary);
  private faConfig=inject(FaConfig);

  ngOnInit(): void {
    this.initFontAwesome();
    // this.faConfig.defaultPrefix = 'fas';
    this.faIconLibrary.addIcons(...fontAwesomeIcons);


  }

  private initFontAwesome() {
    this.faConfig.defaultPrefix = 'far'; // for regular
    this.faIconLibrary.addIcons(...fontAwesomeIcons)
  }
}
