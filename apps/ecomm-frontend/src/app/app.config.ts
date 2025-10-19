import {ApplicationConfig, provideBrowserGlobalErrorListeners, provideZoneChangeDetection,} from '@angular/core';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {appRoutes} from './app.routes';
import {provideClientHydration, withEventReplay,} from '@angular/platform-browser';
import {provideHttpClient, withFetch, withInterceptors} from "@angular/common/http";
import {AbstractSecurityStorage, authInterceptor, LogLevel, provideAuth} from "angular-auth-oidc-client";
import {environmentDevelopment} from "../environments/environment";
import {provideQueryClient, QueryClient} from "@tanstack/angular-query-experimental";
import {SsrStorage} from "./auth/ssr-storage";

export const appConfig: ApplicationConfig = {
  providers: [
    provideClientHydration(withEventReplay()),
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(appRoutes, withComponentInputBinding()),
    provideHttpClient(withFetch(), withInterceptors([authInterceptor()])),
    provideAuth({
      config: {
        authority: 'https://rafea.kinde.com',
        clientId: environmentDevelopment.kinde.clientId,
        redirectUrl: environmentDevelopment.kinde.redirectUri,
        postLogoutRedirectUri: environmentDevelopment.kinde.postLogoutRedirectUri,
        scope: 'openid profile email offline',
        responseType: 'code',
        silentRenew: true,
        useRefreshToken: true,
        logLevel: LogLevel.Warn,
        secureRoutes: [environmentDevelopment.apiUrl],
        customParamsAuthRequest: {
          audience: environmentDevelopment.kinde.audience,
        },
      },
    }),

    {provide: AbstractSecurityStorage, useClass: SsrStorage},
    provideQueryClient(new QueryClient())
  ],
};
