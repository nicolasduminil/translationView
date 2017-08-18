import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';

import { Properties } from './properties';

@Injectable()
export class PropertiesService {

  private url = environment.restCallUrl + 'hello';  // URL to web api

  constructor(private http: HttpClient) { }


  getProperties(): Observable<Object> {
    // Make the HTTP request:
    return this.http.get(this.url + '/config');
      /*.map(data => {
        return new Properties(
          data['environment.signal.colorBackground'],
          data['environment.signal.colorForeground'],
          data['environment.signal.shortName']
        );
      });*/
    }



}
