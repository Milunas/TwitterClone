import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  model: any = {};

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {
  }

  register(){
    this.userService.register(this.model.username, this.model.password);
    this.router.navigate(['login']);
  }

}
