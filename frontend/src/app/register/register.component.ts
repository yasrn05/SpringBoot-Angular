import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  //Khai báo các biến tương ứng với trong form
  phone: string;
  password: string;
  retypePassword: string;

  constructor() {
    this.phone = '';
    this.password = '';
    this.retypePassword = '';
  }
  onPhoneChange() {
    console.log(`Phone typed: ${this.phone}`)
  }
}
