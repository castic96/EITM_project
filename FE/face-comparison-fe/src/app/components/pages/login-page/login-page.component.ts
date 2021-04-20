import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserAuthenticatorService } from '../../../services/user-authenticator/user-authenticator.service';
import { User } from '../../../domain/User';
import { Router } from '@angular/router';
import { Observable, Subject, Subscription } from 'rxjs';
import { WebcamImage, WebcamInitError } from 'ngx-webcam';
import { LoginRequest } from '../../../dto/LoginRequest';
import { QueryService } from '../../../services/query/query.service';
import { IpService } from '../../../services/ip-address/ip.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  // camera
  public showWebcam = true;

  private trigger: Subject<void> = new Subject<void>();

  public webcamImage: WebcamImage | undefined;

  private queryServiceSubscription$: Subscription | undefined;

  private ipAddress: string;

  public isErrorShown = false;
  public errorMessage = '';
  public isCameraError = false;
  public isEmailNeeded = false;

  email: string;

  constructor(private ipService: IpService, private queryService: QueryService,
              private userAuthenticatorService: UserAuthenticatorService,
              private router: Router) {
    this.ipAddress = '';
    this.email = '';
  }

  ngOnInit(): void {
    if (this.userAuthenticatorService.isLogged) {
      this.router.navigate(['/', 'secret']);
    }
    else {
      this.getIPAddress();
    }
  }

  triggerSnapshot(): void {
    if (this.showWebcam) {
      this.trigger.next();
    }
  }

  // camera
  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

  public handleImage(webcamImage: WebcamImage): void {
    console.log('received webcam image', webcamImage);
    this.webcamImage = webcamImage;
    this.tryLogin();
  }

  tryLogin(): void {
    if (!this.webcamImage || this.isCameraError) {
      return;
    }

    if (!this.ipAddress) {
      console.log('problem se zjistenim ip adresy');
      this.ipAddress = '192.168.0.1';
    }

    // if is email needed, then send it
    let emailAddress;
    if (this.isEmailNeeded) {
      if (this.email === '' || this.email === null) {
        console.log('nezadana emailová adresa');
        this.errorMessage = 'You have to fill email addres';
        this.isErrorShown = true;
        return;
      }
      if (this.isEmailAddress(this.email) === false) {
        this.showWebcam = true;
        this.errorMessage = 'Email address is incorrect!';
        this.isErrorShown = true;
        return;
      }
      emailAddress = this.email;
    } else {
      emailAddress = '';
    }

    this.showWebcam = false;
    const loginRequest: LoginRequest = new LoginRequest(
                            this.webcamImage.imageAsBase64,
                            this.ipAddress, emailAddress);

    this.queryServiceSubscription$ = this.queryService.loginQuery(loginRequest).subscribe (data => {
      console.log('prichozi data: ' + data.status);
      if (data.status) {
        this.isErrorShown = false;
        const loggedUser = new User(data.firstName, data.lastName, new Date(), this.email);
        console.log(loggedUser);
        this.userAuthenticatorService.logIn(loggedUser);
        this.router.navigate(['/secret']);
      } else {
        if (data.needEmail) {
          this.isEmailNeeded = true;
          this.errorMessage = 'There are two similar users in the system. Please enter your email address.';
        } else {
          this.errorMessage = 'You are not registered in.';
        }
        this.isErrorShown = true;
        this.showWebcam = true;
      }

    });
  }

  // ngOnDestroy(): void {
  //   if (this.queryServiceSubscription$ !== undefined) {
  //     this.queryServiceSubscription$.unsubscribe();
  //   }
  // }

  private getIPAddress(): void {
    this.ipService.getIPAddress().subscribe(data => {
      this.ipAddress = data.ip;
    });
  }

  public handleInitError(error: WebcamInitError): void {
    console.log('Error in init webcam, message: ' + error.message + ' mediaStreamError:' + error.mediaStreamError);
    this.isErrorShown = true;
    this.isCameraError = true;

    if (error.mediaStreamError && error.mediaStreamError.name === 'NotAllowedError') {
      console.warn('Camera access was not allowed by user!');
      this.errorMessage = 'Camera access was not allowed by user! Allow camera access and reload the page.';

    } else if (error.mediaStreamError && error.mediaStreamError.name === 'NotFoundError') {
      console.warn('Camera not found!');
      this.errorMessage = 'Camera not found! Connect compatible camera and reload the page.';
    }
    else {
      console.warn('Problem with camera!');
      this.errorMessage = 'Problem with camera! Try reload the page.';
    }
  }

  public isEmailAddress(search: string): boolean {
    const regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    return regexp.test(search);
  }
}
