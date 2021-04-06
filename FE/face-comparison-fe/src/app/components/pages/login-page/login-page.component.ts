import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserAuthenticatorService } from '../../../services/user-authenticator/user-authenticator.service';
import { User } from '../../../domain/User';
import { Router } from '@angular/router';
import { Observable, Subject, Subscription } from 'rxjs';
import { WebcamImage } from 'ngx-webcam';
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

  constructor(private ipService: IpService, private queryService: QueryService,
              private userAuthenticatorService: UserAuthenticatorService,
              private router: Router) {
    this.ipAddress = '';
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
    this.tryLogin();
  }

  tryLogin(): void {
    if (!this.webcamImage) { return; }

    if (!this.ipAddress) {
      console.log('problem se zjistenim ip adresy');
      this.ipAddress = '192.168.0.1';
    }

    const loginRequest: LoginRequest = new LoginRequest(
                            this.webcamImage.imageAsBase64,
                            this.ipAddress
    );

    this.queryServiceSubscription$ = this.queryService.loginQuery(loginRequest).subscribe (data => {
      console.log('prichozi data: ' + data.status);
      if (data.status) {
        this.isErrorShown = false;
        const loggedUser = new User(data.firstName, data.lastName, new Date());
        console.log(loggedUser);
        this.userAuthenticatorService.logIn(loggedUser);
        this.router.navigate(['/secret']);
      } else {
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
}
