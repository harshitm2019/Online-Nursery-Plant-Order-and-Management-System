import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';
import { Cart } from '../cart';


@Component({
  selector: 'app-view-cart',
  standalone: true,
  imports: [CommonModule,FormsModule,],
  templateUrl: './view-cart.component.html',
  styleUrl: './view-cart.component.css'
})
export class ViewCartComponent implements OnInit{
  

   cartObj : Cart[] = []; 
   showCartTable : boolean = false;
   showQtyChanged : boolean[] = [];
   showStaticQty : boolean[] = [];
   showCheckoutbutton: boolean = false;

   constructor(

    private route : Router,
    private http : HttpClient,
    private authGuard  :AuthGuardService
   ){
   

   }
   ngOnInit(): void {
   
    this.showQtyChanged = new Array(50).fill(false);
    this.showStaticQty = new Array(50).fill(true);
    this.viewCart();
    
  }


   viewCart(){

              
       this.http.get<Cart[]>("http://localhost:8080/getCartDetails",{headers: {Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(
 
            (cart : Cart[]) =>{
        
                if(cart.length > 0){
                this.showCheckoutbutton = true; 
                this.showCartTable = true;   
                }
                this.cartObj = cart;
                localStorage.setItem('cart', JSON.stringify(cart));
            },
            (error : any) =>{

              this.authGuard.handleError(error);

          }
       );
   }

   remove(plantid : number,idx : number){

        
         this.http.post("http://localhost:8080/deleteItem",plantid,{headers: {Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

               (res : any)=>{

                this.cartObj.splice(idx,1);
                localStorage.setItem('cart',JSON.stringify(this.cartObj));
                if(this.cartObj.length === 0){

                    this.showCartTable = false;
                    this.showCheckoutbutton = false;

                }
                   
              
                
               },
               (error : any) =>{

                this.authGuard.handleError(error);
 
            }
         );
    }

   UpdateQty(plantid : number,idx:number,i:Cart){
          
       this.http.post("http://localhost:8080/updateQty",[plantid,i.qty],{headers: {Authorization: `Bearer ${localStorage.getItem('jwtToken')}`}}).subscribe(

         (res : any)=>{
          
          this.showQtyChanged[idx] = false;
          this.showStaticQty[idx] = true;
          this.cartObj[idx].qty = i.qty;
          localStorage.setItem('cart', JSON.stringify(this.cartObj));


         },
         (error : any) =>{

          this.authGuard.handleError(error);

      }
       );
    }

   

   showQtyChangedBox(idx:number){
    
        this.showQtyChanged[idx] = true;
        this.showStaticQty[idx] = false;

    }  

  showPlantSearch() {
    
        this.route.navigateByUrl("/searchPlants");
      }
  showDashboard(){
 
        this.route.navigateByUrl("/customerDashboard");

    }

    showCheckout(){

      this.route.navigateByUrl("/checkout");

      }
}
