import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PlantMaster } from '../../pages/plant-master';
import { SearchCriteria } from '../../pages/search-criteria';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-plants',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './search-plants.component.html',
  styleUrl: './search-plants.component.css'
})
export class SearchPlants1Component {

  searchCriteriaObj: SearchCriteria;

   plantMasterObj  :PlantMaster[] = [];

   constructor(

     private http : HttpClient,
     private route : Router

   ){

    this.searchCriteriaObj = new SearchCriteria(); 

   }

   searchCriteria() {
        
    this.http.post<PlantMaster[]>("http://localhost:8080/searchPlants",this.searchCriteriaObj).subscribe(

       plant=>{

           this.plantMasterObj = plant;

       },
       (error : any)=>{

          console.error(error);
      }
    );
  }
 
  showLogin(){
    
      this.route.navigateByUrl("/r");

    } 

    showAlert() {
      
     alert("Login Or Signup");
       
    }

}
