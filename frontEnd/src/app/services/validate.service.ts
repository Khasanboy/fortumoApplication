import { Injectable } from '@angular/core';

@Injectable()
export class ValidateService {

  constructor() { }

  validateRegister(user){
    if(user.firstName == undefined || user.lastName == undefined || user.phone == undefined || user.email == undefined || user.password == undefined){
      return false;
    }
    else{
      return true;
    }
  }

  validateEmail(email){
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const pattern = new RegExp(re)
    return pattern.test(email);
  }

  validateSubscription(serviceName, serviceProvider){
    if(serviceName == undefined || serviceProvider == undefined){
      return false;
    }
    else{
      return true;
    }
  }



}
