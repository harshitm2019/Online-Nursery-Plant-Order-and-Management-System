import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class  AuthGuardService implements CanActivate {

      
      constructor(  

            private route : Router,
            private http : HttpClient


      ) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean  {
    
     if(localStorage.getItem('jwtToken') == null){
     this.route.navigateByUrl("/r"); 
     return false;
     }
     else 
     return true;
    
  }
   
  handleError(error : HttpErrorResponse){

       if(error.status === 401){

           window.location.href="/assets/sessionExpired.html";
           
           this.http.get("http://localhost:8080/logout",{}).subscribe(

                (res : any)=>{

                  localStorage.removeItem('jwtToken');
                   
                },
                (error : any)=>{
               
                   console.error(error);

                }
           );   
       }
       else{
           
           console.error(error);

       }
  } 
} 
