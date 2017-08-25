import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Response } from "@angular/http";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import Language from "./language";
import { environment } from "../../../environments/environment";
import { HttpParams } from "@angular/common/http";
import { Subscriber } from "rxjs/Subscriber";

@Injectable()
export class LanguageService {
  private url = environment.languageServiceCallUrl;
 
  public constructor(private http: HttpClient) {}

  public getLanguages(): Observable<Array<Language>> {
    const params = new HttpParams().set("start", '0');
    return this.http.get<Array<Language>>(this.url, { params });
  }
}