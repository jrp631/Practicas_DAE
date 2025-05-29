import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DetailsComponent } from './details/details.component';
import { InicioComponent } from './inicio/inicio.component';
import { VerSerieComponent } from './ver-serie/ver-serie.component';
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
        path: 'details/:id',
        component: DetailsComponent,
        title: 'Home details',
    },
    
];

export default routeConfig;