import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { AuthService } from '../../services/auth.service';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  userData: any;
  userJson: any;

  lastPayment: any;

  nextBillingDate: any;
  serviceType: String;
  serviceProvider: String;
  previousPayments: any[];

  constructor(
    private router: Router,
    private authService: AuthService,
    private flashMessage: FlashMessagesService) { }

  ngOnInit() {

    this.userData = localStorage.getItem("user");
    this.userJson = JSON.parse(this.userData);
    this.lastPayment = JSON.parse(this.userJson.lastBillingDate, this.dateTimeReviver);
    this.nextBillingDate = new Date(this.lastPayment + 2592000000);

    this.serviceType = this.userJson.subscription.name;
    this.serviceProvider = this.userJson.subscription.provider;
    this.previousPayments = this.userJson.billings;
  }

  dateTimeReviver = function (key, value) {
    var a;
    if (typeof value === 'string') {
      a = /\/Date\((\d*)\)\//.exec(value);
      if (a) {
        return new Date(+a[1]);
      }
    }
    return value;
  }

  onUnsubscriptionClick(){

    if(this.userJson.id==""||this.userJson.id==null){
      this.flashMessage.show('Please log in first', {cssClass: 'alert-danger', timeout:3000});
      this.router.navigate(['']);
      return false;
    }

     this.authService.unsubscribeUser(this.userJson.id, this.serviceType).subscribe(data => {
     this.authService.storeUserData(data);
     this.router.navigate(['']);     
    }, err => {
      //console.log(err)
        if(err.status=='404'){
           this.flashMessage.show('User with this id not found', {cssClass: 'alert-danger', timeout:3000});
           
        }
        this.router.navigate(['']);       
    })
  }


}
