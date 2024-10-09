import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/enviroments/enviroment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  @ViewChild('registerForm') registerForm!: NgForm;
  //Khai báo các biến tương ứng với trong form
  phone: string;
  password: string;
  retypePassword: string;
  fullName: string;
  dateOfBirth: Date;
  address: string;
  isAcceped: boolean;

  constructor(private http: HttpClient, private router: Router) {
    // constructor() {
    this.phone = '';
    this.password = '';
    this.retypePassword = '';
    this.fullName = '';
    this.dateOfBirth = new Date();
    this.dateOfBirth.setFullYear(this.dateOfBirth.getFullYear() - 18);
    this.address = '';
    this.isAcceped = true;
  }
  onPhoneChange() {
    console.log(`Phone typed: ${this.phone}`)
    //Valid phone number
  }
  //Check retype password
  checkPasswordsMatch() {
    if (this.password !== this.retypePassword) {
      this.registerForm.form.controls['retypePassword'].setErrors({ 'passwordMismatch': true });
    } else {
      this.registerForm.form.controls['retypePassword'].setErrors(null);
    }
  }
  checkAge() {
    if (this.dateOfBirth) {
      const today = new Date();
      const birthDate = new Date(this.dateOfBirth);
      let age = today.getFullYear() - birthDate.getFullYear();
      const monthDiff = today.getMonth() - birthDate.getMonth();
      if (monthDiff < 0 || (monthDiff == 0 && today.getDate() < birthDate.getDate())) {
        age--;
      }

      if (age < 18) {
        this.registerForm.form.controls['dateOfBirth'].setErrors({ 'invalidAge': true });
      } else {
        this.registerForm.form.controls['dateOfBirth'].setErrors(null);
      }
    }
  }
  register() {
    // const message =
    //   `Phone: ${this.phone} \n` +
    //   `Password: ${this.password} \n` +
    //   `RetypePassword: ${this.retypePassword} \n` +
    //   `FullName: ${this.fullName} \n` +
    //   `DateOfBirth: ${this.dateOfBirth} \n` +
    //   `Address: ${this.address} \n` +
    //   `IsAcceped: ${this.isAcceped}`;
    // alert(message)

    const apiUrl = `${environment.apiUrl}users/register`;
    const registerData = {
      "fullname": this.fullName,
      "phone_number": this.phone,
      "address": this.address,
      "password": this.password,
      "retype_password": this.retypePassword,
      "date_of_birth": this.dateOfBirth,
      "facebook_account_id": 0,
      "google_account_id": 0,
      "role_id": 2
    };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json', });
    this.http.post(apiUrl, registerData, { headers },)
      .subscribe({
        next: (response: any) => {
          if (response && (response.status === 200 || response.status === 201)) {
            this.router.navigate(['/login']);
          } else {
            console.log('ok')
          }
        },
        complete: () => {
        },
        error: (error: any) => {
          alert(`Cannot register, ${error.error} `);
        }
      });
  }
}
