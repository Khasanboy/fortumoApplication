import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'
import {AuthService} from '../../services/auth.service';
import {FlashMessagesService} from 'angular2-flash-messages';
import {ValidateService} from '../../services/validate.service';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent implements OnInit {

   serviceType: String;
   serviceProvider: String;

   userData: any;
   userJson:any;

  constructor(
    private router:Router,
    private authService:AuthService,
    private flashMessage: FlashMessagesService,
    private validateService: ValidateService
  ) { }

  ngOnInit() {

  }

  onSubmit(){

    if(!this.validateService.validateSubscription(this.serviceType, this.serviceProvider)){
      this.flashMessage.show('Please choose items in combobox', {cssClass: 'alert-danger', timeout:3000});
      return false;
    }

    this.userData = localStorage.getItem("user");
    this.userJson = JSON.parse(this.userData);
    //console.log(this.userJson);

    if(this.userJson.id==""||this.userJson.id==null){
      this.flashMessage.show('Please log in first', {cssClass: 'alert-danger', timeout:3000});
      this.router.navigate(['/login']);
      return false;
    }

     this.authService.subscribeUser(this.userJson.id, this.serviceType).subscribe(data => {
     this.authService.storeUserData(data);
     this.router.navigate(['/profile']);     
    }, err => {
      //console.log(err)
        if(err.status=='404'){
           this.flashMessage.show('User with this id not found', {cssClass: 'alert-danger', timeout:3000});
           
        }
        this.router.navigate(['']);       
    })
  }

}
