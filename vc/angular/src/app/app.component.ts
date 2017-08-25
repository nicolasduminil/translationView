import { environment } from '../environments/environment';
import { Component } from '@angular/core';

import { TranslateService } from '@ngx-translate/core';
import { UserService } from './user.service';
import { PropertiesService } from './properties.service';

import { User } from './user';
import { EnvironnementSignal } from './environnementSignal';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  user: User;
  envSignal: EnvironnementSignal;

  constructor(
    private translate: TranslateService, 
    private userService: UserService, 
    private propertiesService: PropertiesService) {console.log('approot')}

  async ngOnInit(): Promise<void> {
    console.log('[AppComponent] - Initialization.');
    this.initTranslate();
    await this.getUser();
    this.getEnvironnementSignal();
  }

  initTranslate():void{
    // this language will be used as a fallback when a translation isn't found in the current language
    this.translate.setDefaultLang('en');
    let browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang);
  }

  getUser() : void{
    this.userService.getUser().then(user => {
      this.user = user;
    });
  }

  getEnvironnementSignal(): void {
    this.propertiesService.getEnvironnementSignal()
      .subscribe((data:EnvironnementSignal) => { 
        this.envSignal = data;
      });
  }

  logout(): void {
    window.location.href =  environment.restCallUrl + 'admin/logout';
  }
}




