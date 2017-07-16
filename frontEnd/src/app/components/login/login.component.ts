import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'
import {AuthService} from '../../services/auth.service';
import {FlashMessagesService} from 'angular2-flash-messages';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email:String;
  password:String;

  constructor( 
    private router:Router,
    private authService:AuthService,
    private flashMessage: FlashMessagesService
  ) {
   
   }

  ngOnInit() {
  }

  onLoginSubmit(){
     this.authService.loginUser(this.email, this.password).subscribe(data => {
      this.router.navigate(['/subscription']);     
    }, err => {
      console.log(err)
        if(err.status=='404'){
           this.flashMessage.show('User with this email addres is not found', {cssClass: 'alert-danger', timeout:3000});
           
        }
        else if(err.status == '400'){
            this.flashMessage.show('Password is wrong', {cssClass: 'alert-danger', timeout:3000});
        }
        this.router.navigate(['']);       
    })
  }



}
