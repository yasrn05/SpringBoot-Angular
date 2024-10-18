import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginDTO } from '../../dtos/user/login.dto';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { TokenService } from 'src/app/services/token.service';
import { LoginResponse } from '../../responses/user/login.response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm!: NgForm;
  phoneNumber: string = '';
  password: string = '';

  constructor(
    private router: Router,
    private userService: UserService,
    private tokenService: TokenService,
  ) { }

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
        next: (response: LoginResponse) => {
          const { token } = response;

          this.tokenService.setToken(token);
          // this.router.navigate(['/login']);
        },
        complete: () => {
        },
        error: (error: any) => {
          alert(error.error.message);
        }
      });
  }
}
