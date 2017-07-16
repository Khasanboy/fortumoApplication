import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { NgModule } from '@angular/core';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';

import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { SubscriptionComponent } from './components/subscription/subscription.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import {ValidateService} from './services/validate.service';


const appRoutes : Routes = [
  {path:'', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'subscription', component:SubscriptionComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    SubscriptionComponent,
    LoginComponent,
    NavbarComponent,
    FooterComponent
  ],
  
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)

  ],
  providers: [ValidateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
