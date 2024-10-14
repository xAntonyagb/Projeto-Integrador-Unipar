import { inject, Injectable } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthRequest } from '../dtos/requests/auth.request';


export const authGuard: CanActivateFn = (route, state) => {
  const auth = inject(AuthRequest);
  const router = inject(Router);

  if (auth.isLoggedIn()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
