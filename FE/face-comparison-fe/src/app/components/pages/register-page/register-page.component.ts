import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthenticatorService } from '../../../services/user-authenticator/user-authenticator.service';
import {Observable, Subject, Subscription} from 'rxjs';
import {WebcamImage} from 'ngx-webcam';
import {User} from '../../../domain/User';
import {IpService} from '../../../services/ip-address/ip.service';
import {QueryService} from '../../../services/query/query.service';
import { RegisterRequest } from '../../../dto/RegisterRequest';

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

  firstName: string;
  lastName: string;

  constructor(private ipService: IpService, private queryService: QueryService,
              private userAuthenticatorService: UserAuthenticatorService,
              private router: Router) {
    this.ipAddress = '';
    this.firstName = '';
    this.lastName = '';
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
    this.trigger.next();
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
    if (!this.webcamImage) { return; }

    if (!this.ipAddress) { console.log('problem se zjistenim ip adresy'); return; }

    if (this.firstName === '') { console.log('nezadane krestni jmeno'); return; }
    if (this.lastName === '') { console.log('nezadane prijmeni'); return; }

    const registerRequest: RegisterRequest = new RegisterRequest(
      this.firstName,
      this.lastName,
      this.webcamImage.imageAsBase64,
      this.ipAddress
    );

    this.queryServiceSubscription$ = this.queryService.registerQuery(registerRequest).subscribe (data => {
      console.log('prichozi data: ' + data.status);
      if (data.status) {
        console.log('if vetev');
      }
      else {
        console.log('else vetev'); // TODO
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
