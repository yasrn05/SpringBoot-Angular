import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginDTO } from '../dtos/user/login.dto';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm!: NgForm;
  phoneNumber: string = '0968698103';
  password: string = '123';

  constructor(private router: Router, private userService: UserService) {
    this.phoneNumber = '';
    this.password = ''
  }
  onPhoneNumberChange() {
    console.log(`Phone typed: ${this.phoneNumber}`)
    //Valid phone number
  }
  login() {
    const message = `phone: ${this.phoneNumber}` +
      `password: ${this.password}`;

    const loginDTO: LoginDTO = {
      "phone_number": this.phoneNumber,
      "password": this.password,
    }
    this.userService.login(loginDTO)
      .subscribe({
        next: (response: any) => {
          // this.router.navigate(['/login']);
        },
        complete: () => {
        },
        error: (error: any) => {
          alert(`Cannot login, ${error.error} `);
        }
      });
  }
}
