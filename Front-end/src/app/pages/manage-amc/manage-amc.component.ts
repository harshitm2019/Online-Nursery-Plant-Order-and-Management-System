import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';

@Component({
  selector: 'app-manage-amc',
  standalone: true,
  imports: [],
  templateUrl: './manage-amc.component.html',
  styleUrl: './manage-amc.component.css'
})
export class ManageAMCComponent {
   
   constructor(

     private route : Router,
     private http : HttpClient,
     private authGuard : AuthGuardService

   ){}


deleteAMC(){

   const input = window.prompt("Enter the Name :");
   if(input != null)
   this.http.post("http://localhost:8080/delete",input,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

        (res : any)=>{
 
           alert("Deleted Successfully");
          
        },
        (error : any) =>{

         this.authGuard.handleError(error);

     }
   );
}

updateAMC(){

  this.route.navigateByUrl("/updateAMC");

}
addAMC(){

    this.route.navigateByUrl("/addAMC");

}

dashboard() {
  
     this.route.navigateByUrl("/staffDashboard");

  }

}
