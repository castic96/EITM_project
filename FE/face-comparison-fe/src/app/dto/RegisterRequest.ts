export class RegisterRequest {
  public firstName: string;
  public lastName: string;
  public email: string;
  public image: string;
  public ipAddress: string;

  constructor(firstName: string, lastName: string, email: string, image: string, ipAddress: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.image = image;
    this.ipAddress = ipAddress;
  }
}
