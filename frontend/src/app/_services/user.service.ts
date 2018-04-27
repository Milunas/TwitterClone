import { Injectable } from "@angular/core";
import { AuthenticationService } from "./authentication.service";
import { Http, RequestOptions, Headers, Response } from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {Twitt} from '../_models/Twitt';


@Injectable()
export class UserService{

    private url = '/user';
    private createUrl = '/user/create';
    private registrationUrl = '/registration';
    private header = new Headers({'Content-Type': 'application/json'});
    private headers = new Headers({     
            'Content-Type': 'application/json',
            'Authorization': '' + this.authenticationService.getToken()
             });
        
    constructor(
        private http: Http,
        private authenticationService: AuthenticationService) { }

    register(username: string, password: string){
        return this.http
        .post(this.registrationUrl, JSON.stringify({username:username,password:password}), {headers:this.header})
        .subscribe()
    }

    getAll() : Promise<Twitt[]> {
       return this.http
             .get(this.url, {headers: this.headers})
             .toPromise()
             .then(this.extractData)
        
    }

    create(message: string, author: string){
        return this.http
        .post(this.createUrl, JSON.stringify({message: message, author: author}), {headers:this.headers})
        .subscribe()
        
    }

    private extractData(res: Response){
        return res.json();
    }
    
}


