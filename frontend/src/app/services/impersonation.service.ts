import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImpersonationService {
  private readonly impersonatedUserIdKey = 'impersonatedUserId';

  constructor() { }

  setImpersonatedUserId(userId: number): void {
    if (this.isBrowser()) {
      console.log(`Impersonated user with ID ${userId}`)
      localStorage.setItem(this.impersonatedUserIdKey, userId.toString());
    }
  }

  getImpersonatedUserId(): number | null {
    if (this.isBrowser()) {
      const userId = localStorage.getItem(this.impersonatedUserIdKey);
      return userId ? parseInt(userId, 10) : null;
    }
    return null;
  }

  clearImpersonatedUserId(): void {
    if (this.isBrowser()) {
      localStorage.removeItem(this.impersonatedUserIdKey);
    }
  }

  private isBrowser(): boolean {
    return typeof window !== 'undefined' && typeof localStorage !== 'undefined';
  }

}
