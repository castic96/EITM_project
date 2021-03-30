import { Component, OnInit } from '@angular/core';
import { UserAuthenticatorService } from '../../../services/user-authenticator/user-authenticator.service';
import { User } from '../../../model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private userAuthenticatorService: UserAuthenticatorService, private router: Router) { }

  ngOnInit(): void {
    if (this.userAuthenticatorService.isLogged) {
      this.router.navigate(['/', 'secret']);
    }
  }

  login(): void {
    this.userAuthenticatorService.logIn(new User('FirstName', 'LastName', new Date()));
  }
}
