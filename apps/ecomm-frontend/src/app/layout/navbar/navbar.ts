import {Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {Oauth2Service} from "../../auth/oauth2-service";
import {faUser, faUserCircle} from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'ecomm-navbar',
  imports: [CommonModule, FaIconComponent],
  standalone: true,
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class NavbarComponent {
  faUser = faUser;

  oauth2Service = inject(Oauth2Service);


  connectedUserQuery :any;
  ngOnInit() {
    this.connectedUserQuery = this.oauth2Service;
  }


  closeDropDownMenu() {
  const bodyElement = document.activeElement  as HTMLBodyElement;
  if (bodyElement) {
    bodyElement.blur();
  }
  }

  login(): void {
    this.closeDropDownMenu();
    this.oauth2Service.login();

  }


  logout(): void {
    this.closeDropDownMenu();
    this.oauth2Service.logout();
  }

  isConnected(): boolean {
    return this.oauth2Service.connectedUserQuery?.data()?.email !== this.oauth2Service.noConnected &&
    this.oauth2Service.connectedUserQuery?.status()==='success';
  }


  protected readonly faUserCircle = faUserCircle;
}
