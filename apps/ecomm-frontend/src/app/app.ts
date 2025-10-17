// Import core Angular functionality for component creation and dependency injection
import {Component, inject, OnInit, PLATFORM_ID} from '@angular/core';
// Import routing modules to enable navigation between different views/pages
import {RouterModule, RouterOutlet} from '@angular/router';
// Import FontAwesome modules to use icons in the application
import {FaConfig, FaIconComponent, FaIconLibrary} from "@fortawesome/angular-fontawesome";
// Import custom FontAwesome icons defined in a separate file
import {fontAwesomeIcons} from "./shared/font-awesome-icons";
// Import a specific icon (home icon) from FontAwesome's solid icons collection
import { faHome } from '@fortawesome/free-solid-svg-icons';
// Import the navigation bar component
import {NavbarComponent} from "./layout/navbar/navbar";
// Import the footer component
import {FooterComponent} from "./layout/footer/footer";
import {Oauth2Service} from "./auth/oauth2-service";
import {isPlatformBrowser} from "@angular/common";
import { Test } from './auth/test';



// Component decorator - defines metadata for the component
@Component({
  // Standalone component - doesn't need to be declared in an NgModule
  standalone:true,
  // List of components/directives used in this component's template
  imports: [FaIconComponent, RouterOutlet,NavbarComponent,FooterComponent],
  // CSS selector for this component - used in HTML as <ecomm-root></ecomm-root>
  selector: 'ecomm-root',
  // HTML template file for this component
  templateUrl: './app.html',
  // Stylesheet file for this component
  styleUrls: ['./app.scss']
})
// Main App component class implementing OnInit lifecycle hook
export class App implements OnInit{
  // Inject FontAwesome icon library service for managing icons
  private faIconLibrary= inject(FaIconLibrary);
  // Inject FontAwesome configuration service for customizing icon behavior
  private faConfig=inject(FaConfig);

  private outh2Service=inject(Oauth2Service);
  private testService = inject(Test);
  platfromId=inject(PLATFORM_ID);


  constructor() {
    if(isPlatformBrowser(this.platfromId)){
      this.outh2Service.initAuthentication()
    }
    this.outh2Service.connectedUserQuery  = this.outh2Service.fetch()
  }

// Lifecycle method called after component initialization
  ngOnInit(): void {
    // Initialize FontAwesome settings
    this.initFontAwesome();
    // Add custom icons to the library (commented out alternative approach)
    // this.faConfig.defaultPrefix = 'fas';
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
    
    // Call getToken to check the access token
    this.testService.getToken();
  }

  // Private method to configure FontAwesome icons
  private initFontAwesome() {
    // Set default icon prefix to 'far' (regular icons)
    this.faConfig.defaultPrefix = 'far'; // for regular
    // Add all custom icons from the fontAwesomeIcons array to the library
    this.faIconLibrary.addIcons(...fontAwesomeIcons)
  }
}