import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { ExcelComponent } from './excel/excel.component';
import { TranslationComponent } from './translation/translation.component';
import { MessageComponent } from './message/message.component';
import { ListTranslationComponent } from './translation/list-translation/list-translation.component';
import { SearchTranslationComponent } from './translation/search-translation/search-translation.component';
import { AdministrationComponent } from './administration/administration.component';
import { LanguageComponent } from './administration/language/language.component';
import { BundleComponent } from './administration/bundle/bundle.component';
import { CacheComponent } from './administration/cache/cache.component';

import { AuthGuardService } from './auth-guard.service';
import { SessionStorage } from 'ngx-webstorage';
import { SessionStorageService } from 'ngx-webstorage';
import { User } from './user';
import { UserService } from './user.service';

const appRoutes: Routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    {
        path: 'translation', component: TranslationComponent,
        children: [
            { path: '', redirectTo: 'list', pathMatch: 'full' },
            { path: 'list', component: ListTranslationComponent },
            { path: 'search', component: SearchTranslationComponent },
        ]
    },
    { path: 'message', component: MessageComponent, canActivate: [AuthGuardService], data: { action: ['canManageMessages'] } },
    { path: 'excel', component: ExcelComponent },
    {
        path: 'administration', component: AdministrationComponent,
        children: [
            { path: 'language', component: LanguageComponent, canActivate: [AuthGuardService], data: { action: ['canManageLanguages'] } },
            { path: 'bundle', component: BundleComponent, canActivate: [AuthGuardService], data: { action: ['canManageBundles'] } },
            { path: 'cache', component: CacheComponent, canActivate: [AuthGuardService], data: { action: ['canRefreshCache'] } }
        ]
    },
];

@NgModule({
    imports: [
        RouterModule.forRoot(
            appRoutes/*,
            { enableTracing: true } // <-- debugging purposes only*/
        )],
    exports: [RouterModule],
    providers: [AuthGuardService],
})
export class AppRoutingModule {}