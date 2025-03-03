import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, } from '@angular/router';

@Component({
  selector: 'app-staff-dashboard',
  standalone: true,
  imports: [HttpClientModule, FormsModule, CommonModule],
  templateUrl: './staff-dashboard.component.html',
  styleUrl: './staff-dashboard.component.css'
})
export class StaffDashboardComponent {

  constructor(

    private http: HttpClient,
    private route: Router,
    private form: FormsModule,
  

  ) {

  }

  managePlants() {
    
      this.route.navigateByUrl("/managePlants");
     
    }

    manageAMC(){

      this.route.navigateByUrl("/manageAMC");

    }

    manageComplaint(){
      
        this.route.navigateByUrl("/manageComplaint");

      }

  logout() {

    this.http.post("http://localhost:8080/logout", {}).subscribe(


      (res: any) => {

        localStorage.removeItem('jwtToken');
        this.route.navigateByUrl("/r");

      },
      (error: any) => {

        console.error("Logout Failed");

      }
    );
  }

  manageOrders(){
  
       this.route.navigateByUrl("/manageOrders");

    }

    viewSalesReport() {
      
         this.route.navigateByUrl("/salesReport");

      }
      

}
