import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Payment } from '../payment';
import { error } from 'console';
import { AuthGuardService } from '../auth-guard.service';

@Component({
  selector: 'app-sales-report',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './sales-report.component.html',
  styleUrl: './sales-report.component.css'
})
export class SalesReportComponent implements OnInit{

  paymentObj : Payment[] = []; 
  from = new Date();
  to = new Date();

  headers = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
    }
  }; 

  constructor(

     private http: HttpClient,
     private route : Router,
     private authGuard : AuthGuardService

  ){}
  ngOnInit(): void{
   
       this.getTotalSales();  

  }

  showDashboard(){
   
       this.route.navigateByUrl("/staffDashboard");
    }
 
    getTotalSales(){

         this.http.get<Payment[]>("http://localhost:8080/getTotalSales",this.headers).subscribe(
 
               pay=>{

                   this.paymentObj = pay;

               },
               (error : any)=>{

                  this.authGuard.handleError(error);

               }                       
         ); 
    }
 
    search(){
      
         this.http.post<Payment[]>("http://localhost:8080/searchByDate",{

           from : this.from,
           to : this.to
         },this.headers).subscribe(
 
           pay =>{

            this.paymentObj = pay;

           },
           (error :any)=>{
              
               this.authGuard.handleError(error);

           }
         );

         this.from = null as any;
         this.to = this.from;
    }
}
