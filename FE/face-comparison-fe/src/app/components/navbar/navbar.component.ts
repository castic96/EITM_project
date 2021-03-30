import { Component, OnInit } from '@angular/core';
import { UserAuthenticatorService } from '../../services/user-authenticator/user-authenticator.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private userAuthenticatorService: UserAuthenticatorService) {
  }

  ngOnInit(): void {
  }

  showLoggedUserMenu(): boolean {
    return this.userAuthenticatorService.isLogged;
  }

  getLoggedUserInitials(): string {
    const loggedUser = this.userAuthenticatorService.loggedUser;
    if (loggedUser !== null) {
      return 'UU';
    } else {
      return '';
    }
  }

  logOut(): void {
    this.userAuthenticatorService.logOut();
  }
}
