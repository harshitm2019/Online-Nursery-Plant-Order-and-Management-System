import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AddOneYearPipe } from '../add-one-year.pipe';
import { AuthGuardService } from '../auth-guard.service';
import { Plantation } from '../plantation';
import { Plantorder } from '../plantorder';

@Component({
  selector: 'app-pending-orders',
  standalone: true,
  imports: [CommonModule,FormsModule,AddOneYearPipe],
  templateUrl: './pending-orders.component.html',
  styleUrl: './pending-orders.component.css'
})
export class PendingOrdersComponent implements OnInit{

    pendingPlantOrders : Plantorder[] = [];  
    showManageOptions : boolean = false;
    showAmcOptions : boolean = false;
    plantationObj : Plantation[]  = [];
    plantOrderNo : string = "";
    i  : number = 0;
    j  : number = 0;
    staffID : any;

   
    

    headers = {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
      }
    };

     
     
     constructor(

        private http : HttpClient,
        private route : Router,
        private authGuard : AuthGuardService

     ){

      let x = localStorage.getItem('staffId');
      let staffId : number = x ? JSON.parse(x) : 0;
      this.staffID = staffId;


     }
  ngOnInit(): void{
    
        this.showPendingOrders(); 

  }
  manageOrder(){
      
   this.route.navigateByUrl("/manageOrders");  

}

dashboard(){

   this.route.navigateByUrl("/staffDashboard");

   }

  manageOrders(plantOrderNo : string,amcOpted : string,idx : number){
     
         
    
       this.j  = idx;
       this.showManageOptions = false;
       this.plantationObj.push(new Plantation());
       this.showManageOptions = true;
       this.plantOrderNo = plantOrderNo;  
       if(amcOpted == "no")
       this.showAmcOptions = true;
       else 
       this.showAmcOptions = false;

  }
  updatePlantationDetails(){
     
     let arr = localStorage.getItem('pendingOrders');

     let temp : Plantorder[] = arr ? JSON.parse(arr) : [];

     this.pendingPlantOrders = temp;
     this.pendingPlantOrders.splice(this.j,1);
     localStorage.setItem('pendingOrders',JSON.stringify(this.pendingPlantOrders));

       this.plantationObj[this.i].plantOrderNo = this.plantOrderNo;
       this.plantationObj[this.i].staffId = this.staffID;
       if(this.showAmcOptions){
       this.plantationObj[this.i].amcStart = null as any;
       this.plantationObj[this.i].amcEnd = null as any;
       }
       this.i++;
       this.showAmcOptions = false;
       this.showManageOptions = false;
       alert("Updated Successfully");

  }

  discard(i: number) {
    
      this.plantationObj.splice(i,1);
      this.showManageOptions = false;
      this.showAmcOptions = false;

    }

updateAmcEnd(event: any,i: number) {

      const currentDate = new Date(event.target.value);
      const  currentDate1 =  new Date(currentDate.getFullYear()+1,currentDate.getMonth(),currentDate.getDate());

      const formattedDate = currentDate1.toISOString().slice(0, 10);
      this.plantationObj[i].amcEnd = formattedDate as any;

}

  showPendingOrders(){

        
        this.http.get<Plantorder[]>("http://localhost:8080/getPendingOrders",this.headers).subscribe(

            plant =>{

               this.pendingPlantOrders = plant;
               localStorage.setItem('pendingOrders',JSON.stringify(plant));
             //  this.plantationObj = [];

            },
            (error : any) =>{

               this.authGuard.handleError(error);

           }

        );       
  }  
  
  submitPlantationDetails(){
    
       this.i = 0;   
       
       
       this.http.post("http://localhost:8080/submitPlantationDetails",this.plantationObj,this.headers).subscribe(

            (res : any)=>{

               alert(res);

            },
            (error : any) =>{

               this.authGuard.handleError(error);

           }
       );
       
       this.plantationObj.splice(0, this.plantationObj.length);
    }


  
}
