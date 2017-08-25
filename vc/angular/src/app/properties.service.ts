import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';

import { EnvironnementSignal } from './environnementSignal';

@Injectable()
export class PropertiesService {

  private url = environment.restCallUrl + 'admin/config';

  constructor(private http: HttpClient) { }

  getEnvironnementSignal(): Observable<EnvironnementSignal> {
    return this.http.get(this.url) 
      .map(data => {
        return new EnvironnementSignal(
          data['environment.signal.colorBackground'],
          data['environment.signal.colorForeground'],
          data['environment.signal.shortName']
        );
      });
  }
}
