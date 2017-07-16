import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {

  constructor(private http:Http) { }
  user:any;

  registerUser(user){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
    headers.append('Access-Control-Allow-Credentials', 'true');
    
    return this.http.post('http://localhost:8080/api/register', user, {headers: headers})
           .map(res => res.json());
  }

  loginUser(email, password){
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('email', email);
    urlSearchParams.append('password', password);
    let body = urlSearchParams.toString()

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
    headers.append('Access-Control-Allow-Credentials', 'true');

     return this.http.post('http://localhost:8080/api/login', body, {headers: headers , search:body})
           .map(res => res.json());

  }

  storeUserData(user){
    localStorage.setItem('user', JSON.stringify(user))
    this.user = user
  }
}
