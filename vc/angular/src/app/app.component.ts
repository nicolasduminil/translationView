import { Component } from '@angular/core';

import { UserService } from './user.service';
import { User } from './user';
import { PropertiesService } from './properties.service';
import { Properties } from './properties';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  backgroundColor = '#18b3b9';
  color = '#ffffff';
  envShortName='PROD';

  user: User;
  properties: Properties;

  constructor(private userService: UserService, private propertiesService: PropertiesService) { }

  ngOnInit(): void {
    this.getUser();
    this.getProperties();
  }

  getUser(): void {
    this.userService.getUser().then(user => {
      this.user = user;
      console.log('<u2>>>', user)
    });
    console.log('<u>>>', this.user)
  }

  getProperties(): void{
    this.propertiesService.getProperties()
      .subscribe(data => {
          console.log('<p2>>>', data['environment.signal.shortName'])

        this.properties = new Properties(
          data['environment.signal.colorBackground'],
          data['environment.signal.colorForeground'],
          data['environment.signal.shortName']
        );
        //this.properties = properties
        console.log('<p2>>>', this.properties)
      });
    console.log('<p>>>', this.properties)
  }

  logout(): void {
    //TODO
  }
}




