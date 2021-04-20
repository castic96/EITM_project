import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginRequest } from '../../dto/LoginRequest';
import { Observable } from 'rxjs';
import { LoginResponse } from '../../dto/LoginResponse';
import { RegisterResponse } from '../../dto/RegisterResponse';
import { RegisterRequest } from '../../dto/RegisterRequest';
import {User} from '../../domain/User';

const httpOptions = {
  headers: new HttpHeaders( {
    'Content-Type': 'application/json'
  })
};


@Injectable({
  providedIn: 'root'
})
export class QueryService {

  backendUrl = 'http://147.228.173.43:8080/';
  loginUrl = 'login';
  registerUrl = 'register';
  testBackendUrl = 'users';

  constructor(private httpClient: HttpClient) {}

  loginQuery(loginRequest: LoginRequest): Observable<LoginResponse> {
    const loginResponse = this.httpClient.post<LoginResponse>(this.backendUrl + this.loginUrl, loginRequest, httpOptions);
    console.log(loginResponse);
    return loginResponse;
  }

  registerQuery(registerRequest: RegisterRequest): Observable<RegisterResponse> {
    return this.httpClient.post<RegisterResponse>(this.backendUrl + this.registerUrl, registerRequest, httpOptions);
  }

  testBackend(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.backendUrl + this.testBackendUrl, httpOptions);
  }
}
