import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-complaint',
  standalone: true,
  imports: [],
  templateUrl: './manage-complaint.component.html',
  styleUrl: './manage-complaint.component.css'
})
export class ManageComplaintComponent {

    constructor(
     
          private route :Router

    ){}
  
    viewPendingOrders(){
     
         this.route.navigateByUrl("/pendingComplaint");

      }
     
      viewCompletedOrders(){

            this.route.navigateByUrl("/completedComplaint");
      }

      dashboard() {
      
        this.route.navigateByUrl("/staffDashboard");

            }
            


}
