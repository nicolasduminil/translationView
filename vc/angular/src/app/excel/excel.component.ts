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
import { ViewEncapsulation } from "@angular/core";


@Component({
  selector: "app-excel",
  templateUrl: "./excel.component.html",
  styleUrls: ['./excel.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ExcelComponent implements OnInit {
  private bundles = new Array<SelectItem>();
  private languages = new Array<SelectItem>();
  private items = new Array<MenuItem>();
  private selectedBundles = new Array<String>();
  private selectedLanguages = new Array<String>();
  private msgs = new Array<Message>();
  private multiple = Boolean(false);
  private auto = Boolean(false);
  private createUpdate = Boolean(false);
  private createOnly = Boolean(false);
  private createMissingMessages = Boolean(false);
  private createMissingBundles = Boolean(false);
  private uploadedFiles: any[] = [];

  constructor(private bundleService: BundleService, private languageService: LanguageService, private downloadService: DownloadService) { }

  private pushBundles(bundlesArray: Array<Bundle>) {
    for (let bundle of bundlesArray)
      this.bundles.push({ label: bundle.label, value: bundle.codePk });
  }

  private pushLanguages(languagesArray: Array<Language>) {
    for (let language of languagesArray)
      this.languages.push({ label: language.label, value: language.codePk });
  }

  public ngOnInit() {
    this.items = [
      { label: "Import", icon: "fa-bar-chart", routerLink: ["/pages/import"] },
      { label: "Export", icon: "fa-twitter", routerLink: ["/pages/export"] }
    ];
    this.bundleService.getBundles().subscribe((bundlesArray: Array<Bundle>) => { this.pushBundles(bundlesArray["bundles"]) },
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
      this.selectedLanguages).map(res => res).subscribe(res => {
        this.msgs.push({ severity: 'info', summary: "File has been downloaded", detail: "Got a file " + res });
        this.selectedBundles.length = 0;
        this.selectedLanguages.length = 0;
      });
  }

  public onUpload(event: any) {
    for (let file of event.files) {
      this.uploadedFiles.push(file);
    }
    console.log("onUpload", this.uploadedFiles);
    this.msgs = [];
    this.msgs.push({ severity: 'info', summary: 'File Uploaded', detail: "Have uploaded files"});
  }
}
