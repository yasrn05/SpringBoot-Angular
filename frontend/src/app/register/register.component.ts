import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

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

  constructor() {
    this.phone = '';
    this.password = '';
    this.retypePassword = '';
    this.fullName = '';
    this.dateOfBirth = new Date();
    this.dateOfBirth.setFullYear(this.dateOfBirth.getFullYear() - 18);
    this.address = '';
    this.isAcceped = false;
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
  register() {
    const message =
      `Phone: ${this.phone} \n` +
      `Password: ${this.password} \n` +
      `RetypePassword: ${this.retypePassword} \n` +
      `FullName: ${this.fullName} \n` +
      `DateOfBirth: ${this.dateOfBirth} \n` +
      `Address: ${this.address} \n` +
      `IsAcceped: ${this.isAcceped}`;
    alert(message)
  }
}
