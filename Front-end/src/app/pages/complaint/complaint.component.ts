import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthGuardService } from '../auth-guard.service';
import { Complaint } from '../complaint';
import { Router } from '@angular/router';

@Component({
  selector: 'app-complaint',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './complaint.component.html',
  styleUrl: './complaint.component.css'
})
export class ComplaintComponent {

   complaintObj : Complaint;

   headers = {

    headers: {
      Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
    }
   };

   constructor(
  
     private http : HttpClient,
     private authGuard : AuthGuardService,
     private route : Router
      
   ){
 
      this.complaintObj = new Complaint();

   }
    
   submit(form:NgForm){
  
        if(form.valid)
        this.http.post("http://localhost:8080/submitComplaint",this.complaintObj,this.headers).subscribe(
          
             (res : any)=>{

                  alert("Submitted successfully");
                  this.route.navigateByUrl("/customerDashboard");

             },
             (error : any)=>{

                 alert("Wrong Plantation Id");
                 this.authGuard.handleError(error);  
             }     
        );

        else 
        alert("Fill All the mandotary Fields");  
    } 
     
    dashboard() {
     
        this.route.navigateByUrl("/customerDashboard");

      }
      

}
