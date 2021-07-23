import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseurl:string="http://localhost:8181";

  constructor(private http:HttpClient) { }
  getEmployeeDetails(data:any){
    console.log(data)
    return this.http.post(`${this.baseurl}/api/postgres/getEmployeeDetails`,data);
}

addEmployeeDetails(data:any){
  console.log(data);
  return this.http.post(`${this.baseurl}/api/postgres/createEmployees`, data);
}
  
}
