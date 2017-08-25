import { Component, OnInit } from '@angular/core';
import { SessionStorage } from 'ngx-webstorage';
import { User } from '../user';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit {
  @SessionStorage('user') user: User;
  
  constructor() { }

  ngOnInit() {
  }

}
