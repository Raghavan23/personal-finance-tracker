import { HttpInterceptorFn } from '@angular/common/http';
import { APP_CONFIG } from '../config';

export const apiInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem(APP_CONFIG.STORAGE_KEYS.AUTH_TOKEN);

  if (token) {
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(authReq);
  }

  return next(req);
};
