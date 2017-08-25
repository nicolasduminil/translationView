import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Response } from "@angular/http";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import Bundle from "./bundle";
import { environment } from "../../../environments/environment";
import { HttpParams } from "@angular/common/http";
import { Subscriber } from "rxjs/Subscriber";

@Injectable()
export class BundleService {
  private url = environment.bundleServiceCallUrl;
 
  constructor(private http: HttpClient) {
  }

  public getBundles(): Observable<Array<Bundle>> {
    const params = new HttpParams().set("start", "0");
    return this.http.get<Array<Bundle>>(this.url, { params });
  }
}