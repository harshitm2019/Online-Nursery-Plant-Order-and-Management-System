import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {


  showloginCard: boolean = false;
  showloginfailedCard: boolean = false;
  
  loginobj: login;
  


  constructor(

    private form: FormsModule,
    private http: HttpClient,
    private route: Router,

  ) {

    this.loginobj = new login();

  }


  showLoginCard(v: boolean) {

    console.log("aksenf"); 
    this.showloginCard = v;

  }

  

  login(num: number) {

    if (num == 1)

      this.http.post("http://localhost:8080/login", this.loginobj).subscribe(
        

        (res: any) => {

          if(res && res.token){
          localStorage.setItem('jwtToken',res.token); 
          
          console.log("Login Successfully");
          this.showloginCard = false;
          this.route.navigateByUrl("/customerDashboard");
          }
          else console.error("token not found");

        },
        (error: any) => {
          
         
          console.error("Login failed", error);
          this.showloginCard = false;
          this.showloginfailedCard = true;

        }
      );

    else

      this.http.post("http://localhost:8080/staffLogin", this.loginobj).subscribe(

        (res: any) => {

          console.log("Login Successfully");
          localStorage.setItem('jwtToken',res.token);
          localStorage.setItem('staffId',res.staffId);
          this.route.navigateByUrl("/staffDashboard");
          this.showloginCard= false;

        },
        (error: any) => {

 
          console.error("Login failed", error);
          this.showloginCard = false;
          this.showloginfailedCard = true;

        }
      );
  }

  showFailedLoginCard() {

    this.showloginfailedCard = false;
    this.showloginCard = true;

  }


}




export class login {


  password: string;

  email: string;

  constructor() {

    this.password = "";
    this.email = "";

  }



}