import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {RequestOptions} from '@angular/http';

@Injectable()
export class AuthService {
  
  user:any;

  constructor(private http:Http) { }
  

  registerUser(user){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
    headers.append('Access-Control-Allow-Credentials', 'true');

    let options = new RequestOptions({ headers: headers });

    return this.http.post('http://localhost:8080/api/register', user, options)
           .map(res => res.json());
  }

  loginUser(phone, password){
   
    let body = {
      'phone': phone,
      'password': password
    }

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
    headers.append('Access-Control-Allow-Credentials', 'true');
    let options = new RequestOptions({ headers: headers });

     return this.http.post('http://localhost:8080/api/login', body, options)
           .map(res => res.json());

  }

   subscribeUser(userid, serviceType){
   
    let body = {
      'id': userid,
      'subscriptionName': serviceType
    }

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
    headers.append('Access-Control-Allow-Credentials', 'true');
    let options = new RequestOptions({ headers: headers });

     return this.http.post('http://localhost:8080/api/subscribe', body, options)
           .map(res => res.json());

  }

   unsubscribeUser(userid, serviceType){
   
    let body = {
      'id': userid,
      'subscriptionName': serviceType
    }

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
    headers.append('Access-Control-Allow-Credentials', 'true');
    let options = new RequestOptions({ headers: headers });

     return this.http.post('http://localhost:8080/api/unsubscribe', body, options)
           .map(res => res.json());

  }


  storeUserData(user){
    localStorage.setItem('user', JSON.stringify(user))
    this.user = user
  }
  
}
