// customer-registration.component.ts

import { HttpClient, HttpClientModule, } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { CustomerDashboardComponent } from '../pages/customer-dashboard/customer-dashboard.component';
import { SignupComponent } from "../pages/customer-signup/signup.component";
import { LoginComponent } from "../pages/login/login.component";
import { RegistrationSuccessComponent } from '../pages/registration-success/registration-success.component';
import { ContactUs } from './contact-us';
import { PlantMaster } from '../pages/plant-master';
import { error } from 'console';
import e from 'express';


@Component({
    selector: 'app-registration',
    standalone: true,
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css'],
    imports: [FormsModule, HttpClientModule, SignupComponent, RegistrationSuccessComponent, LoginComponent,CustomerDashboardComponent,RouterModule,RouterOutlet]
})
export class RegistrationComponent {

  
    contactUsObj : ContactUs;

    constructor(

          private http : HttpClient,
          private route : Router

    ){
        this.contactUsObj = new ContactUs();
    }

    contactUs(){
        
        this.http.post("http://localhost:8080/contactUs",this.contactUsObj).subscribe(

            (res : any)=>{

                alert("Response Submitted");

            },
            (error : any)=>{
                 
                 alert("An error occured");
                 console.error(error);
            }   
        );
 
        this.contactUsObj = null as any; 

    }

    searchPlant(){

        this.route.navigateByUrl("/searchPlants1");

    }

    ourMission() {
        
        window.location.href = '/assets/ourMission.html';
    
    }

    whoWeAre(){

        window.location.href = '/assets/whoWeAre.html';

    }

    ourApproach(){
        
        window.location.href = 'assets/ourApproach.html';
    
    }

    services(){
        
        window.location.href = 'assets/service.html';
    
    }

   
}

