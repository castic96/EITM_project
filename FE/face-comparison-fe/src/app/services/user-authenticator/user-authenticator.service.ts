import {Injectable} from '@angular/core';
import {User} from '../../domain/User';

@Injectable({
  providedIn: 'root'
})
export class UserAuthenticatorService {

  private localStorageKey = 'loggedUser';
  loggedUser: User = new User('', '', new Date());
  isLogged = false;

  constructor() {
    this.init();
  }

  init(): void {
    const userJson = localStorage.getItem(this.localStorageKey);
    if (userJson !== null) {
      this.loggedUser = JSON.parse(userJson);
      this.isLogged = true;
      console.log('Logged: TRUE');
    } else {
      this.isLogged = false;
    }
  }

  logOut(): void {
    this.isLogged = false;
    localStorage.clear();
  }

  logIn(user: User): void {
    localStorage.setItem(this.localStorageKey, JSON.stringify(user));
    this.init();
  }
}
