import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';
import { PlantMaster } from '../plant-master';
import { SearchCriteria } from '../search-criteria';

@Component({
  selector: 'app-admin-search-plant',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './admin-search-plant.component.html',
  styleUrl: './admin-search-plant.component.css'
})
export class AdminSearchPlantComponent {

     searchCriteriaObj : SearchCriteria;
     showPlantBasedOnCriteria : PlantMaster[] = [];
     showSearchCriteriaTable : boolean = false;

     constructor(
 
        private http:HttpClient,
        private route :Router,
        private authGuard : AuthGuardService
     ){
 
        this.searchCriteriaObj = new SearchCriteria();

     }

     dashboard(){
 
         this.route.navigateByUrl("/staffDashboard");
     }

     managePlant(){
         
          this.route.navigateByUrl("/managePlants");

     }

     searchCriteria(){

          if(this.searchCriteriaObj.plantName != "" || this.searchCriteriaObj.plantType != "" || this.searchCriteriaObj.season != "")
          this.http.post<PlantMaster[]>("http://localhost:8080/searchPlant",this.searchCriteriaObj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

           (plantMaster1 : PlantMaster[]) =>{

            this.showPlantBasedOnCriteria = plantMaster1;
            if(this.showPlantBasedOnCriteria.length > 0) this.showSearchCriteriaTable = true;
            else this.showSearchCriteriaTable = false;

            this.searchCriteriaObj.plantName="";
            this.searchCriteriaObj.plantType="";
            this.searchCriteriaObj.season="";

           },
           (error : any) =>{

            this.authGuard.handleError(error);

        }
        );
     }
}


