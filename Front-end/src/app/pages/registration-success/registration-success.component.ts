import { Component, Input } from '@angular/core';
import { SignupComponent } from '../customer-signup/signup.component';




@Component({
  selector: 'app-registration-success',
  standalone: true,
  imports: [SignupComponent],
  templateUrl: './registration-success.component.html',
  styleUrl: './registration-success.component.css'
})
export class RegistrationSuccessComponent {

  @Input() signupComponent!: SignupComponent;

  constructor(){

    
  }

closeCard(){

        this.signupComponent.closeSuccessCard();

}

  


}
