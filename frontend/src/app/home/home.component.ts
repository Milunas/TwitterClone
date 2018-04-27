import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { User } from '../_models/user';
import { Twitt } from '../_models/twitt';
import 'rxjs/Rx';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: User;
  twitts: Twitt[] = [];

  constructor(private userService: UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'))
   }

  ngOnInit() {
    this.loadAll();
  }

  private loadAll(){
    this.userService.getAll().then(twitts => {this.twitts = twitts});
  }

}
