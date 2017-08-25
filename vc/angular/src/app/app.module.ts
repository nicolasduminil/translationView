import { environment } from '../environments/environment';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Ng2Webstorage } from 'ngx-webstorage';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { TabMenuModule, MenuItem } from 'primeng/primeng';
import { TabViewModule } from 'primeng/components/tabview/tabview';
import { GrowlModule } from 'primeng/components/growl/growl';
import { FileUploadModule } from 'primeng/components/fileupload/fileupload';
import { CheckboxModule } from 'primeng/components/checkbox/checkbox';
import { ButtonModule } from 'primeng/components/button/button';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { DialogModule } from 'primeng/components/dialog/dialog';
import { MessagesModule } from 'primeng/components/messages/messages';
import { MultiSelectModule } from 'primeng/components/multiselect/multiselect';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ExcelComponent } from './excel/excel.component';

import { SessionStorageService } from 'ngx-webstorage';
import { PropertiesService } from './properties.service';
import { UserService } from './user.service';
import { BundleService } from './excel/service/bundle.service';
import { LanguageService } from './excel/service/language.service';
import { DownloadService } from './excel/service/download.service';

import { AppRoutingModule } from './app-routing.module';
import { TranslationComponent } from './translation/translation.component';
import { SearchTranslationComponent } from './translation/search-translation/search-translation.component';
import { ListTranslationComponent } from './translation/list-translation/list-translation.component';
import { MessageComponent } from './message/message.component';
import { AdministrationComponent } from './administration/administration.component';
import { LanguageComponent } from './administration/language/language.component';
import { BundleComponent } from './administration/bundle/bundle.component';
import { CacheComponent } from './administration/cache/cache.component';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, environment.restCallUrl + "admin/translations/", "");
}

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ExcelComponent,
    TranslationComponent,
    SearchTranslationComponent,
    ListTranslationComponent,
    MessageComponent,
    AdministrationComponent,
    LanguageComponent,
    BundleComponent,
    CacheComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
    HttpClientModule,
    TabMenuModule,
    TabViewModule,
    FileUploadModule,
    CheckboxModule,
    GrowlModule,
    ButtonModule,
    InputTextModule,
    DataTableModule,
    DialogModule,
    MessagesModule,
    MultiSelectModule,
    Ng2Webstorage,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [UserService, PropertiesService, BundleService, LanguageService, DownloadService],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor(
    private sessionStorage: SessionStorageService
  ) {
    console.log('[AppModule] - Clean session storage.');
    this.sessionStorage.clear();
  }

}
