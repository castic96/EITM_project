import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IpService  {

  constructor(private httpClient: HttpClient) {}

  getIPAddress(): Observable<any> {
    return this.httpClient.get('http://api.ipify.org/?format=json');
  }
}
