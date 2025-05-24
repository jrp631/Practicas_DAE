import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DetailsComponent } from './details/details.component';
import { InicioComponent } from './inicio/inicio.component';

const routeConfig: Routes = [
    {
        path: '',
        // component: InicioComponent,
        // title: 'Inicio Polaflix',
        component: HomeComponent,
        title: 'Home page',
    },
    {
        path: 'details/:id',
        component: DetailsComponent,
        title: 'Home details',
    },
    
];

export default routeConfig;