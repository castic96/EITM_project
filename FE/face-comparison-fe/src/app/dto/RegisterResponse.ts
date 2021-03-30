export class RegisterResponse {
  public status: boolean;
  public errorMessage: string;

  constructor(status: boolean, errorMessage: string) {
    this.status = status;
    this.errorMessage = errorMessage;
  }
}
