export class LoginResponse {
  public status: boolean;
  public firstName: string;
  public lastName: string;

  constructor(status: boolean, firstName: string, lastName: string) {
    this.status = status;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
