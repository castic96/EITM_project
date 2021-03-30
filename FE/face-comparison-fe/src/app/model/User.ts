export class User {
  firstName: string;
  lastName: string;
  loggedDateTime: Date;

  constructor(firstName: string, lastName: string, loggedDateTime: Date) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.loggedDateTime = loggedDateTime;
  }
}
