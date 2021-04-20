export class LoginResponse {
  public status: boolean;
  public firstName: string;
  public lastName: string;
  public needEmail: boolean;

  constructor(status: boolean, firstName: string, lastName: string, needEmail: boolean) {
    this.status = status;
    this.firstName = firstName;
    this.lastName = lastName;
    this.needEmail = needEmail;
  }
}
