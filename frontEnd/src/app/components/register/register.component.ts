import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ValidateService} from '../../services/validate.service';
import {FlashMessagesService} from 'angular2-flash-messages';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName: String;
  lastName:String
  phone: String;
  email: String;
  password: String;

  constructor(private validateService: ValidateService, private flashMessage:FlashMessagesService, private authService:AuthService, private router: Router ) {

   }

  ngOnInit() {
  }


  onRegisterSubmit(){
    console.log('submitted')
    const user ={
      firstName:this.firstName,
      lastName: this.lastName,
      email: this.email,
      password : this.password,
      phone: this.phone
    }

    if(!this.validateService.validateRegister(user)){
      this.flashMessage.show('Please fill in all fields', {cssClass: 'alert-danger', timeout:3000});
      return false;
    }

    if(!this.validateService.validateEmail(user.email)){
      this.flashMessage.show('Please provide valid email address', {cssClass: 'alert-danger', timeout:3000});
      return false;
    }

    this.authService.registerUser(user).subscribe(data => {
      console.log(data);
      if(data){
        this.flashMessage.show('You are registered and can login', {cssClass: 'alert-success', timeout:3000});
        this.router.navigate(['/login']);
      }else{
        this.flashMessage.show('Something went wrong', {cssClass: 'alert-danger', timeout:3000});
        this.router.navigate(['/register']);
      }
    }, err => {
        if(err.status=='409'){
           this.flashMessage.show('User with this email or phone number already registered', {cssClass: 'alert-danger', timeout:3000});
           this.router.navigate(['/register']);
        }
       
    })

  }
}
