import { Component, OnInit } from "@angular/core";
import { Message } from "primeng/components/common/api";
import { MenuItem } from "primeng/components/common/api";
import { SelectItem } from "primeng/components/common/api";
import { BundleService } from "./service/bundle.service";
import { LanguageService } from "./service/language.service";
import { DownloadService } from "./service/download.service";
import Bundle from "./service/bundle";
import Language from "./service/Language";
import { HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs/Observable";


@Component({
  selector: "app-excel",
  templateUrl: "./excel.component.html"
})
export class ExcelComponent implements OnInit {
  private bundles = new Array<SelectItem>();
  private languages = new Array<SelectItem>();
  private items = new Array<MenuItem>();
  private selectedBundles = new Array<String>();
  private selectedLanguages = new Array<String>();
  private msgs = new Array<Message>();


  constructor(private bundleService: BundleService, private languageService: LanguageService, private downloadService: DownloadService) { }

  private pushBundles(bundlesArray: Array<Bundle>) {
    for (let bundle of bundlesArray)
      this.bundles.push({ label: bundle.label, value: /*{codePk: bundle.codePk, label: bundle.label, isActive: bundle.isActive}*/bundle.codePk });
  }

  private pushLanguages(languagesArray: Array<Language>) {
    for (let language of languagesArray)
      this.languages.push({ label: language.label, value: /*{codePk: language.codePk, label: language.label, isActive: language.isActive, isReference: language.isReference}*/language.codePk });
  }

  public ngOnInit() {
    this.items = [
      { label: "Import", icon: "fa-bar-chart", routerLink: ["/pages/import"] },
      { label: "Export", icon: "fa-twitter", routerLink: ["/pages/export"] }
    ];
    this.bundleService.getBundles().subscribe((bundlesArray: Array<Bundle>) => { console.log(bundlesArray); this.pushBundles(bundlesArray["bundles"]) },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log("### Client-side error occured.");
        } else {
          console.log("### Server-side error occured.");
        }
      });
    this.languageService.getLanguages().subscribe((languagesArray: Array<Language>) => { this.pushLanguages(languagesArray["languages"]) },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log("### Client-side error occured.");
        } else {
          console.log("### Server-side error occured.");
        }
      });
  }

  public triggerExport() {
    this.downloadService.downloadFile(this.selectedBundles,
      this.selectedLanguages).subscribe(res => {
        this.msgs.push({ severity: 'info', summary: "File has been downloaded", detail: "Got a file " + res });
        this.selectedBundles.length = 0;
        this.selectedLanguages.length = 0;
      });
  }
}
