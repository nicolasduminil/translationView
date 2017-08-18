import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
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
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { HeroesComponent } from './heroes/heroes.component';
import { HeroService } from './hero.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ExcelComponent } from './excel/excel.component';
import { PropertiesService } from './properties.service';
import { UserService } from './user.service';
import { BundleService } from './excel/service/bundle.service';

import { AppRoutingModule }     from './app-routing.module';

// Imports for loading & configuring the in-memory web api
//import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
//import { InMemoryDataService }  from './in-memory-data.service';
import { HeroSearchComponent } from './hero-search/hero-search.component';

@NgModule({
  declarations: [
    AppComponent,
    HeroDetailComponent,
    HeroesComponent,
    DashboardComponent,
    ExcelComponent,
    HeroSearchComponent
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
    MultiSelectModule
  ],
  providers: [HeroService,UserService,PropertiesService, BundleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
