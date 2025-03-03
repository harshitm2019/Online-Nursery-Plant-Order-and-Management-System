import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthGuardService } from '../auth-guard.service';
import { Complaint } from '../complaint';
import { Service } from '../service';
import { UsedItem } from '../used-item';
import { Router } from '@angular/router';
import { AddOneYearPipe } from '../add-one-year.pipe';

@Component({
  selector: 'app-pending-complaint',
  standalone: true,
  imports: [CommonModule,FormsModule,AddOneYearPipe],
  templateUrl: './pending-complaint.component.html',
  styleUrl: './pending-complaint.component.css'
})
export class PendingComplaintComponent implements OnInit{
  
  complaintObj  : Complaint[]  = [];
  isCostApplicable : boolean = false;
  isOtherCostApplicable : boolean = false;
  usedItemObj : UsedItem[] = [];
  showManageComplaint : boolean = false;
  serviceObj : Service[] = [];
  compId: string = ""
  i : number = 0;
  j : number = 0;

  headers = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
    }
  };
  

  constructor(

        private http : HttpClient,
        private authGuard : AuthGuardService,
        private route : Router

  ){}

  ngOnInit(): void{
    
       this.viewPendingComplaint();

  }

  manageComplaint(idx : number,id : string){

     this.j = idx;
     this.compId = id;
     this.showManageComplaint = true;
     this.serviceObj.push(new Service());

  }

  onAmcChange(event: any){
    
      if(event.target.value == 'No')
      this.isCostApplicable = false;

      else 
      this.isCostApplicable = true;

  }
  
    update(){
  
    this.complaintObj.splice(this.j,1);
    this.serviceObj[this.i].compId  = this.compId;
    this.i++;
    this.showManageComplaint = false;
    this.isOtherCostApplicable = false;
    alert("Updated Successfully");

    
}

  save(){
    
        this.isOtherCostApplicable = false;
        if(this.serviceObj[this.i].usedItems == null)
          alert("You have'nt done any entry");
        else 
          alert("Saved Successfully");   
 }

 onOtherCostChange(event: any){
  
     if(event.target.value == 'Yes'){
     this.isOtherCostApplicable = true;
     this.serviceObj[this.i].usedItems.push(new UsedItem());
     }
     else{

      this.serviceObj[this.i].usedItems.splice(0,this.serviceObj[this.i].usedItems.length);
      this.i = 0;

     }
     
     
}

remove(idx : number){

     this.serviceObj[this.i].usedItems.splice(idx,1);

}
addRow(){
         
     this.serviceObj[this.i].usedItems.push(new UsedItem());

}

  viewPendingComplaint(){

       this.http.get<Complaint[]>("http://localhost:8080/getPendingComplaint",this.headers).subscribe(

           complaint =>{

              this.complaintObj = complaint;
        
           },
           (error : any)=>{
 
             this.authGuard.handleError(error);

      }
    );
  }

  submitServiceDetails(){

        this.http.post("http://localhost:8080/submitServiceDetails",this.serviceObj,this.headers).subscribe(

              (res : any)=>{

                  alert("Submitted Successfully");

              },
              (error : any)=>{

                  this.authGuard.handleError(error);
                  
              }    
        ); 
     }

     dashboard(){
      
         this.route.navigateByUrl("/staffDashboard");
       
      }
      manage_Complaint() {
       
           this.route.navigateByUrl("/manageComplaint");

        }

}
