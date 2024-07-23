import { CanActivateFn,Router } from '@angular/router';
import {  inject } from '@angular/core';
import { UsersService } from './users.service';
export const ClientGuard: CanActivateFn = (route, state) => {
  if (inject(UsersService).isAuthenticated()) {
    return true;
  }else{
    inject(Router).navigate(['/login'])
    return false
  }
};

export const ManagerGuard: CanActivateFn = (route, state) => {
  if (inject(UsersService).isManager()) {
    return true;
  }else{
    inject(Router).navigate(['/login'])
    return false
  }
};

export const ConsultantGuard: CanActivateFn = (route, state) => {
  if (inject(UsersService).isConsultant()) {
    return true;
  }else{
    inject(Router).navigate(['/login'])
    return false
  }
};

