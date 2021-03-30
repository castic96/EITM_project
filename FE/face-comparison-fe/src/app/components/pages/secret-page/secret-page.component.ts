import { Component, OnInit } from '@angular/core';
import { UserAuthenticatorService } from '../../../services/user-authenticator/user-authenticator.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-secret-page',
  templateUrl: './secret-page.component.html',
  styleUrls: ['./secret-page.component.css']
})
export class SecretPageComponent implements OnInit {

  constructor(private userAuthenticatorService: UserAuthenticatorService, private router: Router) { }

  ngOnInit(): void {
    console.log('secret page');
    console.log(this.userAuthenticatorService.isLogged);
    if (!this.userAuthenticatorService.isLogged) {
      this.router.navigate(['/', 'login']);
    }
  }

}
