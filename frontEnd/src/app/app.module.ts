import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { NgModule } from '@angular/core';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';
import {FlashMessagesModule} from 'angular2-flash-messages';

import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { SubscriptionComponent } from './components/subscription/subscription.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import {ValidateService} from './services/validate.service';
import {AuthService} from './services/auth.service';
import { ProfileComponent } from './components/profile/profile.component';
import { PaymenttableComponent } from './shared/paymenttable/paymenttable.component';


const appRoutes : Routes = [
  {path:'', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'subscription', component:SubscriptionComponent},
  {path:'profile', component:ProfileComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    SubscriptionComponent,
    LoginComponent,
    NavbarComponent,
    FooterComponent,
    ProfileComponent,
    PaymenttableComponent
  ],
  
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    FlashMessagesModule

  ],
  providers: [ValidateService, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
