import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddemployeeComponent } from './components/addemployee/addemployee.component';
import { EmployeedetailsComponent } from './components/employeedetails/employeedetails.component';
import { HomeComponent } from './components/home/home.component';


const routes: Routes = [
  {
    path:"getEmployeeDetails",
    component:EmployeedetailsComponent,
    pathMatch:"full"
  },
  {
    path:'',
    component:HomeComponent,
    pathMatch:"full"
  },
  {
    path:"addEmployee",
    component:AddemployeeComponent,
    pathMatch:"full"
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
