import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import Bundle from './bundle';
import { environment } from '../../../environments/environment';
import { HttpParams } from "@angular/common/http";

@Injectable()
export class BundleService {
  private url = environment.bundleServiceCallUrl;
 
  constructor(private http: HttpClient) {
  }

  getBundles(): Observable<Bundle[]> {
    const params = new HttpParams().set('start', '0');
    return this.http.get(this.url, { params }).map((response: Response) => response.json().data as Bundle[]);
    //return this.http.get<Bundle[]>(this.url, { params });
  }
}