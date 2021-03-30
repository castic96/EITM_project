import {Injectable} from '@angular/core';
import {User} from '../../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserAuthenticatorService {

  private localStorageKey = 'loggedUser';
  loggedUser = null;
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
      this.loggedUser = null;
    }
  }

  logOut(): void {
    this.loggedUser = null;
    this.isLogged = false;
    localStorage.clear();
  }

  logIn(user: User): void {
    localStorage.setItem(this.localStorageKey, JSON.stringify(user));
    this.init();
  }
}
