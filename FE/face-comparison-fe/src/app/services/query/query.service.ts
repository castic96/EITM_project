import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginRequest } from '../../dto/LoginRequest';
import { Observable } from 'rxjs';
import { LoginResponse } from '../../dto/LoginResponse';
import { RegisterResponse } from '../../dto/RegisterResponse';
import { RegisterRequest } from '../../dto/RegisterRequest';

const httpOptions = {
  headers: new HttpHeaders( {
    'Content-Type': 'application/json'
  })
};


@Injectable({
  providedIn: 'root'
})
export class QueryService {

  backendUrl = 'http://localhost:8080/';
  loginUrl = 'login';
  registerUrl = 'register';
  testBackendUrl = 'test';

  constructor(private httpClient: HttpClient) {}

  loginQuery(loginRequest: LoginRequest): Observable<LoginResponse> {
    const loginResponse = this.httpClient.post<LoginResponse>(this.backendUrl + this.loginUrl, loginRequest, httpOptions);
    console.log(loginResponse);
    return loginResponse;
  }

  registerQuery(registerRequest: RegisterRequest): Observable<RegisterResponse> {
    return this.httpClient.post<RegisterResponse>(this.backendUrl + this.registerUrl, registerRequest, httpOptions);
  }

  testBackend(): Observable<RegisterResponse> {
    return this.httpClient.get<RegisterResponse>(this.backendUrl + this.testBackendUrl, httpOptions);
  }
}
