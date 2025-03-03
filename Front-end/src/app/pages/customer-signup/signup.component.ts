import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RegistrationSuccessComponent } from '../registration-success/registration-success.component';
import { error } from 'console';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule,HttpClientModule,RegistrationSuccessComponent,CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {


  regobj : Registration; 
  staffregobj: StaffRegistration
  showSuccessCard : boolean = false;
  showFailedCard : boolean = false;
  showStaffSignupCard : boolean = false;
  showCheckAdminPassword : boolean = false;
  showStaffFailedCard : boolean = false;
  

  constructor(

    private form : FormsModule,
    private http: HttpClient,
    private router: Router,
   
  ){
 
      this.regobj = new Registration();
      this.staffregobj = new StaffRegistration();
     

   }

register(form : NgForm) {
  //console.log('s'); 

  if(form.valid)
  this.http.post("http://localhost:8080/register", this.regobj).subscribe(
    (res: any) => {
      console.log("Registration Response:", res);
      this.showSuccessCard = true;
    },
    (error: any) =>{
      console.error("Registration Error:", error);
      this.showFailedCard = true;
    }
  );
  else 
  alert ("Fill all fields");
}
closeSuccessCard() {

  this.showSuccessCard = false;
   // Hide registration success card
}
closeFailedCard(){
 
   this.showFailedCard = false;
   // hide failed card
}

checkAdminPassword(){

     this.http.post("http://localhost:8080/checkAdminPassword",this.staffregobj).subscribe((res : any)=>{


        console.log("Password valid");
        this.staffregobj.password='';
        this.showStaffSignupCard = true;
        this.showCheckAdminPassword = false;
     },
     (error : any)=>{

         alert("Wrong Password Or An error occured");
         this.showCheckAdminPassword = true;

     }
     );
}

staffregister(form : NgForm){
  
  if(form.valid)
  this.http.post("http://localhost:8080/createStaff",this.staffregobj).subscribe(

   (res : any)=>{

      console.log("Account created");
      this.showStaffSignupCard = false;
      this.showSuccessCard = true;
 

   },
   (error : any)=>{

     console.error("Duplicate Error",error);
     this.showStaffSignupCard = false;
     this.showStaffFailedCard = true;

   }
  );
  else
  alert("Fill all fields");

}

showAdminPassword(){
  
  this.showCheckAdminPassword = true;

}

hideAdminPassword() {
  this.showCheckAdminPassword = false;
  }

  hideStaffSignupCard() {
    
     this.showStaffSignupCard = false;
     
    }
    
    closeStaffFailedCard() {
      
        this.showStaffFailedCard = false;
        this.showStaffSignupCard = true;

      }


}
export class Registration{


  cName: string;

  cAdress: string;

  contactNo: number;

  email: string;

  password: string;

  state: string;

  city: string;

  pincode: number;


  constructor() {

    this.cAdress = '';
    this.cName = '';
    this.contactNo = 0 ;
    this.city = '';
    this.email = '';
    this.password = '';
    this.pincode = 0;
    this.state = '';

  }


}

export class StaffRegistration{


  sname: string;

  sAddress: string;

  contactNo: number;

  email: string;

  password: string;

  job : String;

  constructor() {

    this.sAddress = '';
    this.sname = '';
    this.contactNo = 0 ;
    this.email = '';
    this.password = '';
    this.job = '';
   
  }
}




