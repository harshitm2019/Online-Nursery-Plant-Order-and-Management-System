import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Amc } from '../amc';
import { AuthGuardService } from '../auth-guard.service';
import { Cart } from '../cart';
import { Payment } from '../payment';
import { Plantorder } from '../plantorder';


@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit {

  showPlan: boolean = false;

  selectedOption: string = '';
  orderDescription: Cart[] = [];
  totalAmt: number = 0;
  amcObj: Amc[] = [];
  amcOptionYesOrNo: string = '';
  showSelectYesOption: boolean = false;
  checked: boolean = true;
  selectedAmcId: number = 0;
  plantorderObj : Plantorder;
  paymentObj : Payment;
  allowSubmit : boolean = false;
  email : string = "";
   prevAmcCost = 0;
  


  constructor(

    private route: Router,
    private http: HttpClient,
    private formBuilder : FormBuilder,
    private authGuard : AuthGuardService

  ) {

       this.plantorderObj = new Plantorder();
       this.paymentObj = new Payment();
      
  }
  ngOnInit(): void {

    const cartData = localStorage.getItem('cart');
    if (cartData) {
      this.orderDescription = JSON.parse(cartData);
    }

    for (let i = 0; i < this.orderDescription.length; i++) {

      this.totalAmt += this.orderDescription[i].qty * this.orderDescription[i].rate;

    }
}

  handleCheckBox(event: any, amcId: number,amcCost:number) {

    

    if (this.amcOptionYesOrNo == "no" && event.target.checked){

      this.showSelectYesOption = true;

    }
    if (!event.target.checked) {

      this.showSelectYesOption = false;
      this.checked = false;
      this.selectedAmcId = 0;
      
    }
    else if (event.target.checked){

      this.checked = true;
      this.selectedAmcId = amcId;
      this.totalAmt += amcCost - this.prevAmcCost;
      this.prevAmcCost = amcCost;

    }
  }

  onSelectAmcOption(event: any) {

    this.amcOptionYesOrNo = event.target.value;

    if (this.amcOptionYesOrNo == "yes") {
      this.showSelectYesOption = false;
      this.checked = true;
      this.totalAmt += this.prevAmcCost;
    }
    else if (this.checked && this.amcOptionYesOrNo == "no" && this.showPlan) {

      this.showSelectYesOption = true;
      this.checked = false;
      this.totalAmt -= this.prevAmcCost;

    }
  }
  showCart() {

    this.route.navigateByUrl("/viewCart");
  }

  showDashboard() {

    this.route.navigateByUrl("/customerDashboard");

  }
  showPlantSearch() {

    this.route.navigateByUrl("/searchPlants");

  }

  payment(form:NgForm) {
     
    if(this.amcOptionYesOrNo == "yes" && this.selectedAmcId == 0 && this.allowSubmit == true){

        alert("Select AMC plan or Click no");
    }
    else if(this.selectedOption == "" && this.allowSubmit == true){
     
        alert("Select payment mode");

    } 
    else if(form.valid && this.totalAmt > 0){

        this.paymentObj.payAmt = this.totalAmt;
        this.plantorderObj.orderDate.setDate(this.plantorderObj.orderDate.getDate()); 
        this.plantorderObj.amcId = this.selectedAmcId;
        this.plantorderObj.status = "pending";

        const headers = new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
        });
       
        this.http.post("http://localhost:8080/plantOrder",{
        plantOrder : this.plantorderObj,
        payment :this.paymentObj,
        email : this.email
        },
        {
         
           headers:headers,
           responseType : 'arraybuffer'

        }).subscribe(

              (res : ArrayBuffer)=>{
                 
                  alert("Order Placed Successfully");
                  const blob = new Blob([res], { type: 'application/pdf' });
                  const url = window.URL.createObjectURL(blob);
                  window.open(url);
                  window.location.href = '/assets/orderSuccessMsg.html';
                  
              },
              (error : any)=>{
                  
                  this.authGuard.handleError(error);
                  alert("Order Failed"); 
                 
              }
        );

    }
    else {
 
       alert("Some fields are not filled Or no item selected for purchase");

    }
    
    // else 
    // this.http.post("http://localhost:8080/plantOrder",[this.plantorderObj,this.paymentObj])
}


  showAmcPlan() {

    this.showPlan = true;

    this.http.get<Amc[]>("http://localhost:8080/getAmcPlan", { headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}` } }).subscribe(

      amc => {

        this.amcObj = amc;
      },
      (error : any) =>{

        this.authGuard.handleError(error);

    }

    );
  }

  onSelect(event: any) {

    this.selectedOption = event.target.value;
  
  }

}
