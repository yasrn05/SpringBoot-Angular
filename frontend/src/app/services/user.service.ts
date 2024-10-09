import { Injectable } from "@angular/core";
import { environment } from 'src/enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs";
import { RegisterDTO } from "../dtos/register.dto";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private apiUrl = `${environment.apiUrl}users/register`;

    constructor(private http: HttpClient) { }
    register(registerDTO: RegisterDTO): Observable<any> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json', });
        return this.http.post(this.apiUrl, registerDTO, { headers })
    }
}