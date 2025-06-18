import { Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { VerSerieComponent } from './ver-serie/ver-serie.component';
import { NotFoundComponent } from './not-found.component';
const routeConfig: Routes = [
    {
        path: '',
        // component: InicioComponent,
        // title: 'Inicio Polaflix',
        component: InicioComponent,
        title: 'Home page',
    },
    {
        path:'ver-serie/:id',
        component: VerSerieComponent,
        title: 'Ver Serie',
    },
    {
        path: '**',
        component: NotFoundComponent,
        title: 'Page not found',
    }

];

export default routeConfig;