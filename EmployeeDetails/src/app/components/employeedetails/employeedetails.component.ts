import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmployeeService } from 'src/app/service/employee.service';

export interface PeriodicElement {
  Name: string;
  ID: number;
  JobTitle: string;
  Age: string;
  startDate:string;
  endDate:string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {ID: 1, Name: 'Hydrogen', JobTitle: 'Software Engineer', Age: '33-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 2, Name: 'Helium', JobTitle: 'Software Engineer', Age: '33-Years',startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 3, Name: 'Lithium', JobTitle: 'Software Engineer', Age: '34-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 4, Name: 'Beryllium', JobTitle: 'Software Engineer', Age: '33-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 5, Name: 'Boron', JobTitle: 'Software Engineer', Age: '32-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 6, Name: 'Hydrogen', JobTitle: 'Software Engineer', Age: '33-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 7, Name: 'Helium', JobTitle: 'Software Engineer', Age: '33-Years',startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 8, Name: 'Lithium', JobTitle: 'Software Engineer', Age: '34-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 9, Name: 'Beryllium', JobTitle: 'Software Engineer', Age: '33-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 10, Name: 'Boron', JobTitle: 'Software Engineer', Age: '32-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 11, Name: 'Hydrogen', JobTitle: 'Software Engineer', Age: '33-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 12, Name: 'Helium', JobTitle: 'Software Engineer', Age: '33-Years',startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 13, Name: 'Lithium', JobTitle: 'Software Engineer', Age: '34-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 14, Name: 'Beryllium', JobTitle: 'Software Engineer', Age: '33-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  {ID: 15, Name: 'Boron', JobTitle: 'Software Engineer', Age: '32-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
  
];


@Component({
  selector: 'app-employeedetails',
  templateUrl: './employeedetails.component.html',
  styleUrls: ['./employeedetails.component.css']
})
export class EmployeedetailsComponent implements OnInit {

  data={
    employeeName:"",
    startDate:"",
    endDate:""
  }
  constructor(private employeeservice:EmployeeService, private snak:MatSnackBar) { }

  ngOnInit() {
  }
  doSubmitForm(){
    console.log("form is submitting...");
    console.log("data: "+this.data);

    if(this.data.employeeName=='' || this.data.startDate=='' || this.data.endDate==''){
      this.snak.open("Fields cannot be empty","cancel");
      return;
  }
  
    this.employeeservice.getEmployeeDetails(this.data).subscribe(
      response=>{
          console.log(response)
      },
      error=>{
        console.log(error);
      }
    );
  }

  displayedColumns: string[] = ['ID', 'Name', 'JobTitle', 'Age','startDate','endDate'];
  dataSource = ELEMENT_DATA;
}
