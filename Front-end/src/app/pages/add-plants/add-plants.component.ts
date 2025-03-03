import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { PlantMaster } from '../plant-master';
import { AuthGuardService } from '../auth-guard.service';

@Component({
  selector: 'app-add-plants',
  standalone: true,
  imports: [HttpClientModule,CommonModule,FormsModule],
  templateUrl: './add-plants.component.html',
  styleUrl: './add-plants.component.css'
})
export class AddPlantsComponent {
  
      plantObj : PlantMaster;

         constructor(

           private http:HttpClient,
           private route : Router,
           private authGuard : AuthGuardService

         ){

              this.plantObj = new PlantMaster();
              
         }

         addPlants(form :NgForm) {
         
             if(form.valid)
             this.http.post("http://localhost:8080/addPlant",this.plantObj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}` } }).subscribe(

                  (res :any)=>{

                       console.log("Plant added");
                       alert("Data added successfully");
                        

                  },
                  (error : any)=>{
                     
                     this.authGuard.handleError(error);
                     console.error(error);
                     alert("Data not added");
                  }   
             );
             else 
             alert("Fill All Fields");

             form.resetForm();

          }     
     

      dashboard() {
         
             this.route.navigateByUrl("/staffDashboard");

        }
        managePlants() {
        
              this.route.navigateByUrl("/managePlants");

          }
}
