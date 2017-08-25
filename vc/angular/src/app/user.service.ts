import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { HttpClient, HttpParams } from '@angular/common/http';
//import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';
import { SessionStorage } from 'ngx-webstorage';
import { SessionStorageService}  from 'ngx-webstorage';

import 'rxjs/add/operator/toPromise';

import { User } from './user';

@Injectable()
export class UserService {
  @SessionStorage('user') user: User;

  private userURL = environment.restCallUrl + 'admin';  // URL to web api

  constructor(private http: Http, private httpClient: HttpClient, private sessionStorage:SessionStorageService,) { }

  async getUser(): Promise<User> { 
    if (this.user != null) {
      return this.user;
    }else{
      const url = `${this.userURL}/whoami`;
      const user = await this.httpClient.get<User>(url).toPromise();
      this.sessionStorage.store('user', user);
      return user;
    }
  }

  // getUser(): Promise<User> {
  //   const url = `${this.userURL}/whoami`;
  //   return this.http.get(url)
  //     .toPromise()
  //     .then(response => response.json() as User)
  //     .catch(this.handleError);
  // }

  // private handleError(error: any): Promise<any> {
  //   console.error('An error occurred', error); // for demo purposes only
  //   return Promise.reject(error.message || error);
  // }

  hasRightForAction(action: string): Promise<boolean> {
    const url = `${this.userURL}/hasAction/${action}`;
    return this.httpClient.get<boolean>(url).toPromise();
  }
}
