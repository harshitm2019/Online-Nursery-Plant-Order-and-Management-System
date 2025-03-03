import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '../auth-guard.service';
@Component({
  selector: 'app-customer-dashboard',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule],
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.css']
})
export class CustomerDashboardComponent {


  showViewDetails : boolean = false;
  showChangePassword : boolean = false;
  showChangeAddress : boolean = false;
  showChangeContact : boolean = false;
  showPasswordChangedMsg : boolean = false;
  cusObj  : customer;
  passwordObj : password;
  passwordStatusMsg : string = "";
  isError : boolean = false;
  addressObj : Address;
  showAddressChangedMsg : boolean = false;
  addressStatusMsg : string = "";
  contactobj : Contact;
  showContactChangedMsg : boolean = false;
  contactStatusMsg : string = "";


   constructor(

     private http : HttpClient,
     private form : FormsModule,
     private route : Router,
     private authGuard : AuthGuardService

   ){

      this.cusObj = new customer();
      this.passwordObj = new password();
      this.addressObj = new Address();
      this.contactobj = new Contact();

   }
  
  showDetails() {

     this.showViewDetails = true;
     this.http.get<customer>
     ("http://localhost:8080/getCustomer", { headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}` } }).subscribe(
       
      cusObj1=> {
          
         this.cusObj = cusObj1;
         
      },
      (error : any) =>{

        this.authGuard.handleError(error);

    }
    );
}

 changePassword(){

       
      this.http.post("http://localhost:8080/changePasswordCustomer",this.passwordObj, { headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}` } }).subscribe(

      (res: any) => {

        console.log("Password changed successfully");
        this.hidePasswordCard();
        this.showPasswordChangedMsg = true;
        this.passwordStatusMsg = "Password Changed";
        this.isError=false
      },
      (error: any) => {
           
           this.authGuard.handleError(error);
           this.passwordStatusMsg = "Password not changed";
           this.hidePasswordCard();
           this.showPasswordChangedMsg = true;
           console.error("Password not changed");
           this.isError=true
       
      }
      );
 }

changeAdress(){

       this.http.post("http://localhost:8080/changeAddress",this.addressObj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}` } }).subscribe(


          (res : any) =>{

               console.log("Address Changed Successfully");
               this.hideAddressCard();
               this.showAddressChangedMsg = true;
               this.addressStatusMsg = "Address changed successfully";
               this.isError = false;
               
          },
          (error : any) =>{
               
               this.authGuard.handleError(error);
               this.hideAddressCard();
               this.showAddressChangedMsg = true;
               this.addressStatusMsg = "Address not changed";
               this.isError = true;
          }
       );
}

changeContact(){

     this.http.post("http://localhost:8080/changeContact",this.contactobj,{ headers: { Authorization: `Bearer ${localStorage.getItem('jwtToken')}` } }).subscribe(

        (res : any) =>{

           console.log("Contact Changed");
           this.contactStatusMsg = "Contact changed successfully";
           this.hideContactCard();
           this.showContactChangedMsg = true;
           this.isError = false;
        },
        (error : any) => {

            this.authGuard.handleError(error);
            this.hideContactCard();
            this.contactStatusMsg = "Contact not changed";
            this.showContactChangedMsg = true;
            this.isError = true;

        });
}

logout() {

       localStorage.removeItem('jwtToken');
       localStorage.removeItem('cart');

       this.http.get<any>("http://localhost:8080/logout").subscribe(

           (res : any)=>{

                 localStorage.clear();
                  this.route.navigateByUrl("/r");
      
           },
           (error : any)=>{

                console.error(error);

           }



       );
}


hideDetailsCard() {
  this.showViewDetails = false;
}

showPassword() {
  this.showChangePassword = true;
}

hidePasswordCard() {
  this.showChangePassword = false;
}

hidePasswordChangedMsg(){

    this.showPasswordChangedMsg = false;

}
hideAddrssChangedMsg(){

     this.showAddressChangedMsg = false;

}

showAddress() {
  this.showChangeAddress = true;
}

hideAddressCard() {
  this.showChangeAddress = false;
}

showContact() {
  this.showChangeContact = true;
}

hideContactCard() {
  this.showChangeContact = false;
}

hideContactChangedMsg(){

  this.showContactChangedMsg = false;

}

searchPlants() {
  
  this.route.navigateByUrl("/searchPlants");

  }

  myorders() {
     
       this.route.navigateByUrl("/myOrders");
     
    }  

    showComplaintForm() {
      
       this.route.navigateByUrl("/complaintForm");

      }  


      showComplaintDetails() {
        
         this.route.navigateByUrl("/viewComplaint");

        }
}

export class customer{


     email : string;

     contactNo : number;

     cAdress : string;

     city : string;

     state : string;

     constructor(){

           this.email = ""
           this.contactNo = 0;
           this.cAdress = "";
           this.state = "";
           this.city="";
     }
}

export class password{

   param1 : string;

   param2 : string;

   constructor(){

       this.param1 = "";
       this.param2 = "";

   }
}
export class Address{

    city : string;
    state : string;
    pincode :number;
    address : string;

    constructor(){

        this.city = "";
        this.state= "";
        this.address = "";
        this.pincode = 0;

    }

}

export class Contact{
 
     param1 : number;

     constructor(){

         this.param1 = 0;

     }


}