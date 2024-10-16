import { Injectable } from "@angular/core";
import { environment } from 'src/enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs";
import { RegisterDTO } from "../dtos/user/register.dto";
import { LoginDTO } from "../dtos/user/login.dto";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    constructor(private http: HttpClient) { }
    private createHeader(): HttpHeaders {
        return new HttpHeaders({ 'Content-Type': 'application/json', });
    }
    private apiConfig = {
        headers: this.createHeader(),
    }

    private apiRegister = `${environment.apiBaseUrl}users/register`;
    private apiLogin = `${environment.apiBaseUrl}users/login`;


    register(registerDTO: RegisterDTO): Observable<any> {
        return this.http.post(this.apiRegister, registerDTO, this.apiConfig)
    }

    login(loginDTO: LoginDTO): Observable<any> {
        return this.http.post(this.apiLogin, loginDTO, this.apiConfig)
    }
}