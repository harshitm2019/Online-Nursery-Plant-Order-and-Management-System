import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-manage-plants',
  standalone: true,
  imports: [],
  templateUrl: './manage-plants.component.html',
  styleUrl: './manage-plants.component.css'
})
export class ManagePlantsComponent {

searchPlant() {

    this.route.navigateByUrl("/adminSearchPlant");

}

    constructor(

        private route : Router

    ){}

addPlants(){

    this.route.navigateByUrl("/addPlants");
}

updatePlants(){

    this.route.navigateByUrl("/updatePlants");
}

dashboard() {

     this.route.navigateByUrl("/staffDashboard");
    
    }

}
