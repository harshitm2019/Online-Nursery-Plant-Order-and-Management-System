import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';

@Component({
  selector: 'app-updateplants',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './updateplants.component.html',
  styleUrl: './updateplants.component.css'
})
export class UpdateplantsComponent implements OnInit{


    plantobj : updatePlants;
    arr : number[] = [];
     
   constructor(

      private http:HttpClient,
      private route : Router,
      private authGuard : AuthGuardService

   ){

      this.plantobj = new updatePlants();

   }
   ngOnInit(): void {
      
        this.http.get<number[]>("http://localhost:8080/getPlantId",{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

             a=>{
                 
                this.arr = a;
             },
             (error  :any)=>{

                   this.authGuard.handleError(error);  
             }
        );

   }
   setId(event : any) {
      
       this.plantobj.plantId = event.target.value;
       
      }

   onSubmit(form : NgForm){
 
    form.resetForm();

 }

   updatePlant(){

         this.http.post("http://localhost:8080/updatePlant",this.plantobj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

           (res : any)=>{

                 console.log("Update Successfully");
                 alert("Data updated successfully");

           },
           (error : any) =>{

              this.authGuard.handleError(error);
              alert("Updation Failed");

           }
        );

   }

   dashboard() {
         
    this.route.navigateByUrl("/staffDashboard");

}
managePlants() {

     this.route.navigateByUrl("/managePlants");

 }

}

export class updatePlants{

  plantId : number;

  plantName : string;

  plantType : string;

  season : string;

  benefits : string;

  rate : number;

  imgAddr : string;
     
  constructor(){

       this.plantName = "";
       this.plantType = "";
       this.season = "";
       this.benefits = "";
       this.rate = 0;
       this.plantId = 0;
       this.imgAddr = "";
  }
}
