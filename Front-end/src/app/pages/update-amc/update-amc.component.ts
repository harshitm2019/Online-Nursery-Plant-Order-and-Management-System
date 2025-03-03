import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Amc } from '../amc';
import { AuthGuardService } from '../auth-guard.service';

@Component({
  selector: 'app-update-amc',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule],
  templateUrl: './update-amc.component.html',
  styleUrl: './update-amc.component.css'
})
export class UpdateAmcComponent implements OnInit{


   id : number[] = [];
   amcObj : Amc;
   selectedValue : number = 0;
   
   
 constructor(
 
   private http : HttpClient,
   private route : Router,
   private authGuard : AuthGuardService

 ){

    this.amcObj = new Amc();

 }

 ngOnInit(): void {
   
  if(this.id != null)
  this.http.get<number[]>("http://localhost:8080/getId",{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

  id1 =>{

    this.id = id1;
    localStorage.setItem('amcId',JSON.stringify(id1));

  },
  (error : any) =>{

    this.authGuard.handleError(error);

}
);
}


manageAMC(){

   this.route.navigateByUrl("/manageAMC");

}
dashboard() {

     this.route.navigateByUrl("/staffDashboard");
}

updateAmcPlan(){

 this.amcObj.amcId = this.selectedValue; 
 
  this.http.post("http://localhost:8080/addAmcPlan",this.amcObj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

     (res : any)=>{

         alert("Data added successfully");

     },
     (error : any) =>{

      this.authGuard.handleError(error);

  }
);
}

}
