import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { RegistrationComponent, } from './registration/registration.component';
import {  HttpClientModule, } from '@angular/common/http';
import { CustomerDashboardComponent } from './pages/customer-dashboard/customer-dashboard.component';
import { LoginComponent } from './pages/login/login.component';
import { SearchPlantsComponent } from './pages/search-plants/search-plants.component';
import { PdfViewerComponent, PdfViewerModule } from 'ng2-pdf-viewer';


@Component({
  selector: 'app-root',
  standalone: true,
  imports : [RegistrationComponent ,HttpClientModule,CustomerDashboardComponent,RouterOutlet,LoginComponent,SearchPlantsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'online-nursery-plant';

  

}
