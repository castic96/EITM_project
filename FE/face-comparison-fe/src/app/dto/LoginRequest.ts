export class LoginRequest {
  public image: string;
  public ipAddress: string;

  constructor(image: string, ipAddress: string) {
    this.image = image;
    this.ipAddress = ipAddress;
  }
}
