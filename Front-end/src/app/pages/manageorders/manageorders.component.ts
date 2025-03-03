import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manageorders',
  standalone: true,
  imports: [],
  templateUrl: './manageorders.component.html',
  styleUrl: './manageorders.component.css'
})
export class ManageordersComponent {


   constructor(

      private route : Router

   ) {}


showPendingOrders() {
 
    this.route.navigateByUrl("/pendingOrders");
    
}

showCompletedOrders() {

    this.route.navigateByUrl("/completedOrders");

}

dashboard() {
  
   this.route.navigateByUrl("/staffDashboard");

  }

}
