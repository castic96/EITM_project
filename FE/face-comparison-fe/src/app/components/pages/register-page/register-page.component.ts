import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthenticatorService } from '../../../services/user-authenticator/user-authenticator.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  constructor(private userAuthenticatorService: UserAuthenticatorService, private router: Router) { }

  ngOnInit(): void {
    if (this.userAuthenticatorService.isLogged) {
      this.router.navigate(['/', 'secret']);
    }
  }

}
