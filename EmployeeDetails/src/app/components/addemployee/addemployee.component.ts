import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/service/employee.service';

interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-addemployee',
  templateUrl: './addemployee.component.html',
  styleUrls: ['./addemployee.component.css']
})
export class AddemployeeComponent implements OnInit {

  data={
    firstName:"",
    lastName:"",
    jobTitle:"",
    emailId:"",
    phoneNumber:"",
    age:""
  }

  constructor(private empservice:EmployeeService) { }

  ngOnInit() {
  }

  selectedValue: string;
  
  foods: Food[] = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'}
  ];

  addEmployeeDetailsForm(){
    console.log("employee details form submit...");
    this.empservice.addEmployeeDetails(this.data).subscribe(
      response=>{
          console.log(response);
      },
      error=>{
        console.log(error);
      }
    );
  }
}
