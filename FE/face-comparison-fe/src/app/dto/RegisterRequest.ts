export class RegisterRequest {
  public firstName: string;
  public lastName: string;
  public image: string;
  public ipAddress: string;

  constructor(firstName: string, lastName: string, image: string, ipAddress: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.image = image;
    this.ipAddress = ipAddress;
  }
}
