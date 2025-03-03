import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';
import { Plantation } from '../plantation';

@Component({
  selector: 'app-completed-orders',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './completed-orders.component.html',
  styleUrl: './completed-orders.component.css'
})
export class CompletedOrdersComponent implements OnInit{


   plantationObj : Plantation[] = [];
   plantDate : any;
   staffId : any;
   plantationId: any;
   plantOrderNo : any;
   showUpdateCard : boolean = false;
   showAmcOptions : boolean = false;
   set = new Set<number>();
   i : number = 0;


   headers = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
    }
  }; 

 
   constructor(

     private http : HttpClient,
     private route : Router,
     private authGuard : AuthGuardService 

   ){}
  ngOnInit(): void{
    
    this.showCompletedOrders();

  }
 
    showCompletedOrders(){

       this.http.get<Plantation[]>("http://localhost:8080/getCompletedOrders",this.headers).subscribe(
 
           plant =>{

             this.plantationObj = plant;
  
           },
           (error : any) =>{

               this.authGuard.handleError(error);

           }
      ); 
    }
     
    search(){
     
          this.http.post<Plantation[]>("http://localHost:8080/getPlantationDetailsOnSearch",{
          plantDate : this.plantDate,
          staffId : this.staffId,
          plantOrderNo : this.plantOrderNo,
          plantationId : this.plantationId
        }
       ,this.headers).subscribe(

             plant =>{

                this.plantationObj = plant;

             },
             (error : any) =>{

              this.authGuard.handleError(error);

          }

       );

         this.plantOrderNo = null;
         this.plantationId = null;
         this.staffId = null;
         this.plantDate = null;
       

    }
    
    update(plantationId: number,idx  : number ,amcStart : Date | null,staffId : number){
       
        
        this.i = idx;
        let sId = localStorage.getItem('staffId');

        let temp : number = sId ? JSON.parse(sId) : 0;

        if(temp == staffId)
        this.showUpdateCard = true;
        else {
        this.showUpdateCard = false;
        alert("You cannot acces it");
        }
        if(amcStart == null) this.showAmcOptions = true;
    }

    submit(p: Plantation){
        
         
         this.set.add(this.i);      
         this.showUpdateCard = false;
         this.showAmcOptions = false;
         alert("Updated Successfully");
      
    }

    discard(){

        this.showUpdateCard = false;

    }

    submitAll(){

        let arr : number[] =  Array.from(this.set);
    
          this.http.post("http://localhost:8080/submitUpdateDetails",[this.plantationObj,JSON.stringify(arr)],this.headers).subscribe(

                 (res : any)=>{

                     alert(res);

                 },
                 (error : any)=>{
                     
                    this.authGuard.handleError(error);

                 }
          ); 
    }
       
      manageOrder(){
      
          this.route.navigateByUrl("/manageOrders");  

      }
      dashboard(){

        this.route.navigateByUrl("/staffDashboard");
     
        }
}
