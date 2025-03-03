import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';
import { Plantorder } from '../plantorder';

@Component({
  selector: 'app-my-orders',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.css'
})
export class MyOrdersComponent implements OnInit{

  plantOrderObj : Plantorder[] = [];
  arr1  :ArrayBuffer[] = []

 constructor(

   private route : Router,
   private http : HttpClient,
   private authGuard : AuthGuardService

 ){}

  ngOnInit(): void {
    
    this.showOrders();
    
  }

  showOrders(){

    this.http.get<Plantorder[]>("http://localhost:8080/getOrders",{headers: {Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

          plantorder =>{

             this.plantOrderObj = plantorder;
            
            },
          (error : any) =>{

            this.authGuard.handleError(error);

        }
    );

    }
 
showDashboard(){

  this.route.navigateByUrl("/customerDashboard");

}

}
