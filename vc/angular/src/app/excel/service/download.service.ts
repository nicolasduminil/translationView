import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Response } from "@angular/http";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import { environment } from "../../../environments/environment";
import { HttpParams } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { Subscriber } from "rxjs/Subscriber";
import { RequestOptions, ResponseContentType, RequestMethod } from '@angular/http';

@Injectable()
export class DownloadService {
  private url = environment.downloadServiceCallUrl;

  public constructor(private http: HttpClient) { }

  public downloadFile(bundleIds: Array<String>, langIds: Array<String>): Observable<String> {
    let headers = new HttpHeaders({ "Content-Type": "application/json" });
    return this.http.post(this.url, { bundleIds: bundleIds, langIds: langIds }, { headers, responseType: 'text' });
  }
}