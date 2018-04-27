import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { Router } from '@angular/router';
import { User } from '../_models/user';
import { AuthenticationService } from '../_services/authentication.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  model: any;
  currentUser: User;
  constructor(private authService: AuthenticationService, 
              private userService: UserService,  
              private router: Router) {
      }

  ngOnInit() {
  }

 create(){
   console.log(this.authService.getUsername())
   this.userService.create(this.model, this.authService.getUsername());
   this.router.navigate(['home']);
  }

}
