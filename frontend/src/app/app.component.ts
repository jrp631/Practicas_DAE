import { Component } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { RouterModule } from '@angular/router';
import { SeriesUsuarioComponent } from "./series-usuario/series-usuario.component";
import { InicioComponent } from "./inicio/inicio.component";
import { SeriesService} from "./series.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HomeComponent, RouterModule, SeriesUsuarioComponent, InicioComponent],
  template: `
    <header>
      <div class="barra-superior">
        <div class="logo">
          <a [routerLink]="['/']">
          <img src="assets/images/polaflix.png" alt="Logo">
          </a>
        </div>
        <div class="usuario">
          <span>Bienvenido Jon Nieve</span>
          <img src="assets/images/user.png" alt="Usuario">
        </div>
      </div>
      <nav class="menu">
        <a [routerLink]="['/']">Inicio</a>
        <a [routerLink]="['/']">Agregar Serie</a>
        <a href="#">Ver Carros</a>
      </nav>
    </header>
    <router-outlet></router-outlet>
    <!-- <app-serie-usuario></app-serie-usuario> -->
    <!-- <app-inicio></app-inicio> -->
  `,
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Polaflix';

  constructor(private seriesService: SeriesService) {
    this.seriesService.getUserSeriesPendientes().then(console.log);
    this.seriesService.getUserSeriesEmpezadas().then(console.log);
    this.seriesService.getUserSeriesTerminadas().then(console.log);
  }
}
