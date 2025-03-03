import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';
import { Complaint } from '../complaint';
import { Service } from '../service';
import { UsedItem } from '../used-item';

@Component({
  selector: 'app-completed-complaint',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './completed-complaint.component.html',
  styleUrl: './completed-complaint.component.css'
})
export class CompletedComplaintComponent implements OnInit {


  serviceObj: Service[] = [];
  complaintObj: Complaint[] = [];
  i = 0;
  showServiceOption: boolean = false;
  showMaintainenance: boolean = false;
  showOtherCost : boolean = false;
  showAmcOption: string = "";
  showOtherCostOption : string = "";
  set = new Set<number>();

  headers = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('jwtToken')}`
    }
  };



  constructor(

    private http: HttpClient,
    private route: Router,
    private authGuard: AuthGuardService,
  
    
  ) { 

    

  }

  ngOnInit(): void {

    this.showCompletedComplaint();

  }

  dashboard() {
  
       this.route.navigateByUrl("/staffDashboard");

    }
    manageComplaint() {
     
       this.route.navigateByUrl("/manageComplaint");
       
    }

  showCompletedComplaint() {

    this.http.get<Service[]>("http://localhost:8080/getServiceDetails", this.headers).subscribe(

      service => {

        this.serviceObj = service;
        localStorage.setItem('serv', JSON.stringify(service));

      },
      (error: any) => {

        this.authGuard.handleError(error);

      }
    );
  }

  update(idx: number) {

    this.i = idx;
    this.showServiceOption = true;
    this.handleMaintainenanceCost();
    this.handleOtherCost();
    localStorage.setItem('update',JSON.stringify(this.serviceObj[this.i]));

  }

  handleMaintainenanceCost() {

    if (this.serviceObj[this.i].cost == null) {

      this.showMaintainenance = true;
      this.showAmcOption = "Yes";

    }
    else {

      this.showMaintainenance = false;
      this.showAmcOption = "No";

    }

  }

  handleOtherCost(){

           if(this.serviceObj[this.i].usedItems.length == 0){

              this.showOtherCost = false;
              this.showOtherCostOption  = "No";

          }
          else {

                 this.showOtherCost = true;
                 this.showOtherCostOption = "Yes";
      }
  }

  onChange(event: any) {
    
           if(event.target.value == "No")  
            this.showOtherCost = false;

           else if(event.target.value == "Yes")
            this.showOtherCost = true;               
    }

  onAmcChange(event: any) {

    if (event.target.value == "Yes")
      this.showMaintainenance = true;

    else if (event.target.value == "No")
      this.showMaintainenance = false;

  }

  addRow(){
    
        this.serviceObj[this.i].usedItems.push(new UsedItem());
     
    }

    deleteRow(idx: number){
      
         this.serviceObj[this.i].usedItems.splice(idx,1);

      }

      saveItem(){
       
            this.set.add(this.i); 
            this.showOtherCost  = false;
            this.showServiceOption = false;
            alert("Saved Successfully");   
        }
       discard(){

           let arr =  localStorage.getItem('update');

           let array : Service = arr ? JSON.parse(arr) : [];

           this.serviceObj[this.i] = array;

           alert("Changes Discard");

       } 

       submitAll(){
  
           let arr =  Array.from(this.set);
     
           this.http.post("http://localhost:8080/submitUpdatedServiceDetails",[this.serviceObj,JSON.stringify(arr)],this.headers).subscribe(


               (res : any)=>{

                   alert("Submitted Successfully");
                   this.route.navigateByUrl("/manageComplaint");

               },
               (error : any)=>{

                   this.authGuard.handleError(error);
            }
        );
    }
}
