import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginRequest } from '../../dto/LoginRequest';
import { Observable } from 'rxjs';
import { LoginResponse } from '../../dto/LoginResponse';
import { RegisterResponse } from '../../dto/RegisterResponse';
import { RegisterRequest } from '../../dto/RegisterRequest';

const httpOptions = {
  headers: new HttpHeaders( {
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Methods': 'GET, POST',
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json; charset=UTF-8'
  })
};

@Injectable({
  providedIn: 'root'
})
export class QueryService {

  backendUrl = 'http://localhost:8080/';
  loginUrl = 'login';
  registerUrl = 'register';

  constructor(private httpClient: HttpClient) {}

  loginQuery(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(this.backendUrl + this.loginUrl, loginRequest, httpOptions);
  }

  registerQuery(registerRequest: RegisterRequest): Observable<RegisterResponse> {
    return this.httpClient.post<RegisterResponse>(this.backendUrl + this.registerUrl, registerRequest, httpOptions);
  }

}
