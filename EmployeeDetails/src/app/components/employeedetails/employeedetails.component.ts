import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmployeeService } from 'src/app/service/employee.service';

export interface EmployeeDetails {
  Name: string;
  ID: number;
  JobTitle: string;
  Age: string;
  startDate:string;
  endDate:string;
}

const ELEMENT_DATA: EmployeeDetails[] = [
  {ID: 1, Name: 'Vikas', JobTitle: 'Software Engineer', Age: '33-Years', startDate:'18-07-2021',endDate:'20-07-2021'},
    
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

  empdata:any=[];
  constructor(private employeeservice:EmployeeService, private snak:MatSnackBar) { }

  ngOnInit() {
  }
  doSubmitForm(){
    console.log("form is submitting...");
    console.log("data: "+this.data);

  //    if(this.data.employeeName==''){
  //      this.snak.open("Fields cannot be empty","cancel");
  //      return;
  //  }
  
  
    this.employeeservice.getEmployeeDetails(this.data).subscribe(
      response=>{
          console.log(response)
          this.empdata=response;
      },
      error=>{
        console.log(error);
      }
    );
  }

  displayedColumns: string[] = ['ID', 'Name', 'JobTitle', 'Age','startDate','endDate'];
  dataSource = this.empdata;
}
