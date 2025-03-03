import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAmcComponent } from './pages/add-amc/add-amc.component';
import { AddPlantsComponent } from './pages/add-plants/add-plants.component';
import { AdminSearchPlantComponent } from './pages/admin-search-plant/admin-search-plant.component';
import { AuthGuardService } from './pages/auth-guard.service';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { ComplaintComponent } from './pages/complaint/complaint.component';
import { CompletedOrdersComponent } from './pages/completed-orders/completed-orders.component';
import { CustomerDashboardComponent } from './pages/customer-dashboard/customer-dashboard.component';
import { LoginComponent } from './pages/login/login.component';
import { ManageAMCComponent } from './pages/manage-amc/manage-amc.component';
import { ManageComplaintComponent } from './pages/manage-complaint/manage-complaint.component';
import { ManagePlantsComponent } from './pages/manage-plants/manage-plants.component';
import { ManageordersComponent } from './pages/manageorders/manageorders.component';
import { MyOrdersComponent } from './pages/my-orders/my-orders.component';
import { PendingComplaintComponent } from './pages/pending-complaint/pending-complaint.component';
import { PendingOrdersComponent } from './pages/pending-orders/pending-orders.component';
import { SearchPlantsComponent } from './pages/search-plants/search-plants.component';
import { StaffDashboardComponent } from './pages/staff-dashboard/staff-dashboard.component';
import { UpdateAmcComponent } from './pages/update-amc/update-amc.component';
import { UpdateplantsComponent } from './pages/updateplants/updateplants.component';
import { ViewCartComponent } from './pages/view-cart/view-cart.component';
import { ViewComplaintComponent } from './pages/view-complaint/view-complaint.component';
import { RegistrationComponent } from './registration/registration.component';
import { CompletedComplaintComponent } from './pages/completed-complaint/completed-complaint.component';
import { SalesReportComponent } from './pages/sales-report/sales-report.component';
import { SearchPlants1Component } from './registration/search-plants/search-plants.component';


export const routes: Routes = [

   {path: ""  ,redirectTo : "/r" ,pathMatch:"full"},
   {path:"r",component:RegistrationComponent},
   {path:"searchPlants1",component:SearchPlants1Component},
   {path: "login" , component : LoginComponent},
   {path: "customerDashboard" , component : CustomerDashboardComponent,canActivate:[AuthGuardService]},
   {path: "searchPlants" ,component : SearchPlantsComponent,canActivate:[AuthGuardService]},
   {path : "viewCart" , component : ViewCartComponent,canActivate:[AuthGuardService]},
   {path: "checkout",component : CheckoutComponent,canActivate:[AuthGuardService]},
   {path: "myOrders",component:MyOrdersComponent,canActivate:[AuthGuardService]},
   {path: "complaintForm" , component:ComplaintComponent,canActivate:[AuthGuardService]},
   {path:"viewComplaint",component:ViewComplaintComponent, canActivate:[AuthGuardService]},
   {path:"staffDashboard",component : StaffDashboardComponent ,canActivate:[AuthGuardService]},
   {path:"managePlants",component:ManagePlantsComponent,canActivate:[AuthGuardService]},
   {path:"addPlants",component:AddPlantsComponent,canActivate:[AuthGuardService]},
   {path:"updatePlants",component:UpdateplantsComponent,canActivate:[AuthGuardService]},
   {path:"adminSearchPlant",component:AdminSearchPlantComponent,canActivate:[AuthGuardService]}, 
   {path:"manageAMC",component:ManageAMCComponent,canActivate:[AuthGuardService]},
   {path:"addAMC",component:AddAmcComponent,canActivate:[AuthGuardService]},
   {path:"updateAMC",component:UpdateAmcComponent,canActivate:[AuthGuardService]},
   {path:"manageOrders",component:ManageordersComponent,canActivate:[AuthGuardService]},
   {path:"pendingOrders",component:PendingOrdersComponent,canActivate:[AuthGuardService]},
   {path:"completedOrders",component:CompletedOrdersComponent,canActivate:[AuthGuardService]},
   {path:"manageComplaint",component:ManageComplaintComponent,canActivate:[AuthGuardService]},
   {path:"pendingComplaint",component:PendingComplaintComponent,canActivate:[AuthGuardService]},
   {path:"completedComplaint",component:CompletedComplaintComponent,canActivate:[AuthGuardService]},
   {path:"salesReport",component:SalesReportComponent,canActivate:[AuthGuardService]},
  
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })

  export class ApprRoutingModule{}


