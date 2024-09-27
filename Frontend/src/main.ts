import { catchError } from 'rxjs/operators';
import { provideRouter } from '@angular/router';
import { AppModule } from './app/app.module';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { bootstrapApplication } from '@angular/platform-browser';
import { AmbientesModule } from './app/ambientes/ambientes.module';
import { routes } from './app/app.routes';

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

bootstrapApplication(AmbientesModule, {
  providers: [provideAnimationsAsync(),provideRouter(routes)],
}).catch((err) => console.error(err));
