import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { environment } from '../environments/environment';

import 'rxjs/add/operator/toPromise';

import { User } from './user';

@Injectable()
export class UserService {

  private userURL = environment.restCallUrl + 'hello';  // URL to web api

  constructor(private http: Http) { }

  getUser(): Promise<User> {
    const url = `${this.userURL}/whoami`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
