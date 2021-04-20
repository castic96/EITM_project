import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserAuthenticatorService} from '../../../services/user-authenticator/user-authenticator.service';
import {Observable, Subject, Subscription} from 'rxjs';
import {WebcamImage, WebcamInitError} from 'ngx-webcam';
import {IpService} from '../../../services/ip-address/ip.service';
import {QueryService} from '../../../services/query/query.service';
import {RegisterRequest} from '../../../dto/RegisterRequest';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  // camera
  public showWebcam = true;

  private trigger: Subject<void> = new Subject<void>();

  public webcamImage: WebcamImage | undefined;

  private queryServiceSubscription$: Subscription | undefined;

  private ipAddress: string;

  public isErrorShown = false;
  public errorMessage = '';
  public isCameraError = false;

  firstName: string;
  lastName: string;
  email: string;

  constructor(private ipService: IpService, private queryService: QueryService,
              private userAuthenticatorService: UserAuthenticatorService,
              private router: Router) {
    this.ipAddress = '';
    this.firstName = '';
    this.lastName = '';
    this.email = '';
  }

  ngOnInit(): void {
    if (this.userAuthenticatorService.isLogged) {
      this.router.navigate(['/', 'secret']);
    } else {
      this.getIPAddress();
    }
  }

  triggerSnapshot(): void {
    if (this.showWebcam) {
      this.trigger.next();
      this.showWebcam = false;
    }
  }

  // camera
  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

  public handleImage(webcamImage: WebcamImage): void {
    console.log('received webcam image', webcamImage);
    this.webcamImage = webcamImage;
    this.register();
  }

  register(): void {
    console.log('zacatek register');
    if (!this.webcamImage || this.isCameraError) {
      return;
    }

    if (!this.ipAddress) {
      console.log('problem se zjistenim ip adresy');
      this.ipAddress = '192.168.0.1';
      return;
    }

    if (this.firstName === '' || this.firstName === null) {
      this.showWebcam = true;
      console.log('nezadane krestni jmeno');
      this.errorMessage = 'You have to fill first name';
      this.isErrorShown = true;
      return;
    }
    if (this.lastName === '' || this.lastName == null) {
      console.log('nezadane prijmeni');
      this.errorMessage = 'You have to fill last name';
      this.isErrorShown = true;
      return;
    }
    if (this.email === '' || this.email == null) {
      console.log('nezadany email');
      this.errorMessage = 'You have to fill email';
      this.isErrorShown = true;
      return;
    }

    this.isErrorShown = false;
    this.showWebcam = false;

    const registerRequest: RegisterRequest = new RegisterRequest(
      this.firstName,
      this.lastName,
      this.email,
      this.webcamImage.imageAsBase64,
      this.ipAddress
    );

    this.queryServiceSubscription$ = this.queryService.registerQuery(registerRequest).subscribe(data => {
      console.log('prichozi data: ' + data.status);

      if (data.status) {
        console.log('if vetev');
        this.router.navigate(['/', 'login']);
      }
      else {
        const errorMessage = data.errorMessage;
        console.log('ERROR: ' + errorMessage);

        this.errorMessage = errorMessage;
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

}
