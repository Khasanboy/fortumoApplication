import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ValidateService} from '../../services/validate.service'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  name: String;
  surname:String
  phone: String;
  email: String;
  password: String;

  constructor(
    private validateService: ValidateService,
    private router: Router
  ) {

   }

  ngOnInit() {
  }


  onRegisterSubmit(){
    const user ={
      name:this.name,
      surname: this.surname,
      phone: this.phone,
      email: this.email,
      password : this.password
    }

    if(!this.validateService.validateRegister(user)){
      return false;
    }

    if(!this.validateService.validateRegister(user.email)){
      return false;
    }

  }
}
