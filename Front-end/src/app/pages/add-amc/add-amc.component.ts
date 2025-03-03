import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Amc } from '../amc';
import { AuthGuardService } from '../auth-guard.service';

@Component({
  selector: 'app-add-amc',
  standalone: true,
  imports: [HttpClientModule,CommonModule,FormsModule],
  templateUrl: './add-amc.component.html',
  styleUrl: './add-amc.component.css'
})
export class AddAmcComponent {

  amcObj : Amc;
    
 constructor(

       private http : HttpClient,
       private route : Router,
       private authGuard : AuthGuardService

 ){

     this.amcObj = new Amc();

 }

 dashboard() {
  
    this.route.navigateByUrl("/staffDashboard");
   
  }
  manageAMC(){

    this.route.navigateByUrl("/manageAMC");

  }

  addAmcPlan(form : NgForm){

        if(form.valid) 
        this.http.post("http://localhost:8080/addAmcPlan",this.amcObj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

           (res : any)=>{

               alert("Data added successfully");

           },
           (error : any)=>{

            this.authGuard.handleError(error);

        }
    );
    else
    alert("Fill All Fields");
  
  }
}
