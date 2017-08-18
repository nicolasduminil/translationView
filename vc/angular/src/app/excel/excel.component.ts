import { Component, OnInit } from '@angular/core';
import { Message } from 'primeng/components/common/api';
import { MenuItem } from 'primeng/components/common/api';
import { SelectItem } from 'primeng/components/common/api';
import { BundleService } from './service/bundle.service';
import Bundle from './service/bundle';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-excel',
  templateUrl: './excel.component.html'
})
export class ExcelComponent implements OnInit {
  bundles: SelectItem[];
  selectedBundles: string[] = [];
  languages: SelectItem[];
  selectedLanguages: string[] = [];
  activeItem: MenuItem;
  msgs: Message[] = [];
  private items: MenuItem[];

  constructor(private bundleService: BundleService) { }

  generateBundles(bundlesArray: Bundle[]) {
    for (let bundle of bundlesArray) {
      this.bundles.push({label: bundle.label, value: {codePk: bundle.codePk, label: bundle.label, isActive: bundle.isActive}});
    }
  }

  ngOnInit() {
    this.items = [
      { label: 'Import', icon: 'fa-bar-chart', routerLink: ['/pages/import'] },
      { label: 'Export', icon: 'fa-twitter', routerLink: ['/pages/export'] }
    ];
    this.activeItem = this.items[2];
    this.bundleService.getBundles().subscribe((bundlesArray: Bundle[]) => {
      /*for (let b of bundlesArray)
        console.log(b);*/
      this.generateBundles(bundlesArray);
   });
  }
  
  onChangeStep(label: string) {
    this.msgs.length = 0;
    this.msgs.push({ severity: 'info', summary: label });
  }
}
