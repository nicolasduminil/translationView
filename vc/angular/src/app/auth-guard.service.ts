
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { User } from './user';
import { UserService } from './user.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor( private userService: UserService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    let action = route.data["action"];
    console.log('action', action);
    const hasRight = this.hasRight(action)
    console.log('hasRight', hasRight);
    return hasRight;
  }

  // async hasRight(action: string): Promise<boolean> {
  //   if (action != undefined) {
  //     if (this.user != null) {
  //       return !this.user[action];
  //     }
  //   }
  //   return false;
  // }

  // async hasRight(action:string) : Promise<boolean>{
  //   let toto:boolean = false;
  //   if (this.user != null) {
  //     console.log("HEREEE");
  //     return this.user[action]
  //   }else{
  //     console.log('USER IS NULL');
  //     let toto:boolean = await this.userService.hasRightForAction("I18N_MANAGE_MESSAGES");
  //     console.log('toto', toto);
  //     return toto;
  //   }
  // }

  async hasRight(action:string) : Promise<boolean>{
    const user = await this.userService.getUser();
    console.log('user', user);
    return user[action];
  }

}