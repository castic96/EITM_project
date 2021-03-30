export class User {
  private _firstName: string;
  private _lastName: string;
  private _loggedDateTime: Date;

  constructor(firstName: string, lastName: string, loggedDateTime: Date) {
    this._firstName = firstName;
    this._lastName = lastName;
    this._loggedDateTime = loggedDateTime;
  }

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get lastName(): string {
    return this._lastName;
  }

  set lastName(value: string) {
    this._lastName = value;
  }

  get loggedDateTime(): Date {
    return this._loggedDateTime;
  }

  set loggedDateTime(value: Date) {
    this._loggedDateTime = value;
  }
}
