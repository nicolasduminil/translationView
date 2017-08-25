import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { SessionStorage } from 'ngx-webstorage';
import { User } from '../user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @SessionStorage('user') user: User;
  userURL = environment.restCallUrl; 

  constructor() { }

  ngOnInit(): void {

  }



  
}
