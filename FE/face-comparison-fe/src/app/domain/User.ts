export class User {
  firstName: string;
  lastName: string;
  loggedDateTime: Date;
  email: string;

  constructor(firstName: string, lastName: string, loggedDateTime: Date, email: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.loggedDateTime = loggedDateTime;
    this.email = email;
  }
}
