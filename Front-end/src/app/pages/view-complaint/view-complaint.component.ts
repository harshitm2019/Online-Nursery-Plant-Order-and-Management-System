import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Complaint } from '../complaint';
import { error } from 'console';
import { AuthGuardService } from '../auth-guard.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-complaint',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './view-complaint.component.html',
  styleUrl: './view-complaint.component.css'
})
export class ViewComplaintComponent implements OnInit{

  complaintObj : Complaint[] = []; 

  headers = {

    headers: {
      Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
    }
   };


  constructor(

      private route : Router,
      private http : HttpClient,
      private authGuard : AuthGuardService

  ){}

  ngOnInit(): void{

       this.getComplaintDetails();
    
  }

  showDashboard() {
   
        this.route.navigateByUrl("/customerDashboard");

    }

    getComplaintDetails(){

           this.http.get<Complaint[]>("http://localhost:8080/getComplaintDetails",this.headers).subscribe(

               complaint =>{

                   this.complaintObj = complaint;

               },
               (error : any)=>{

                     this.authGuard.handleError(error);
               }
           );          
    }

}
