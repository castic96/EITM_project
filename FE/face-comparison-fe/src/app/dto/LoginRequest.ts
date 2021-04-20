export class LoginRequest {
  public image: string;
  public ipAddress: string;
  public email: string;

  constructor(image: string, ipAddress: string, email: string) {
    this.image = image;
    this.ipAddress = ipAddress;
    this.email = email;
  }
}
