import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';
import { Cart } from '../cart';
import { PlantMaster } from '../plant-master';
import { SearchCriteria } from '../search-criteria';
import { ViewCartComponent } from '../view-cart/view-cart.component';

@Component({
  selector: 'app-search-plants',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './search-plants.component.html',
  styleUrl: './search-plants.component.css'
})
export class SearchPlantsComponent {

    searchCriteriaObj : SearchCriteria; 
    plantMaster : PlantMaster[] = [];
    showPlantDetails : boolean = false;
    quantities: number[] = [];
    addToCartMsg : string = "";
    showAddToCartMsg: boolean[] = [];
    cartObj : Cart;
    viewObj : ViewCartComponent;
  
 constructor(

   private route : Router,
   private http:HttpClient,
   private authGuard  : AuthGuardService  
   
 ){

     this.searchCriteriaObj = new SearchCriteria();
     this.cartObj = new Cart();
     this.viewObj = new ViewCartComponent(route,http,authGuard);
  
 }

 addToCart(plantid : number,qty:number,plantname: string,rate:number,idx : number){
 
      this.showAddToCartMsg[idx] = true;
      this.cartObj.plantid = plantid;
      this.cartObj.qty = qty;
      this.cartObj.plantname = plantname;
      this.cartObj.rate = rate;
       
      this.http.post("http://localhost:8080/addToCart",this.cartObj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

          
          (res : any)=>{

                this.addToCartMsg = "Added successfully";
                setTimeout(()=>{
                   this.showAddToCartMsg[idx] = false;
                },3000)
          },
          (error : any)=>{

            this.authGuard.handleError(error); 
            this.addToCartMsg = "Item not added";
            setTimeout(() => {
              this.showAddToCartMsg[idx] = false;
            },3000); 
        }
    );   
}

searchCriteria(form : NgForm){
    
    if(this.searchCriteriaObj.plantType != "" || this.searchCriteriaObj.plantName != null)
    this.http.post<PlantMaster[]>("http://localhost:8080/searchPlant",this.searchCriteriaObj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

         (plantM : PlantMaster[])=>{

              if(plantM.length > 0){
              this.plantMaster = plantM;
              this.showPlantDetails = true;
              }
              else this.showPlantDetails = false;
         },
         (error : any) =>{

          this.authGuard.handleError(error);

      }  
    );
    
    form.resetForm();
    this.searchCriteriaObj.plantName = "";
    this.searchCriteriaObj.season = "";

}

 showDashboard() {

    this.route.navigateByUrl("/customerDashboard").then(()=>{
  
    });

}

showCart(){

   this.route.navigateByUrl("/viewCart");
   
}

}


