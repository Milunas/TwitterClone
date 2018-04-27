import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService{
    
    private url = '/login'
    private authHeader = 'authorization'

    constructor(private http: Http) { }

    login(username: string, password: string): Observable<boolean>{
        return this.http.post(this.url, JSON.stringify({username: username, password: password}))
            .map((response: Response) => {
                if(response.headers.has(this.authHeader)){
                    localStorage.setItem('currentUser',
                         JSON.stringify({username: username, token: response.headers.get(this.authHeader)}))
                    return true;
                }
                    return false;
                });
    }

    logout(){
        localStorage.removeItem('currentUser');
    }

    getToken(): String{
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var token = currentUser && currentUser.token;
        return token ? token : "";        
    }

    getUsername(): string{
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        var username = currentUser && currentUser.username;
        return username ? username : "";  
    }

}