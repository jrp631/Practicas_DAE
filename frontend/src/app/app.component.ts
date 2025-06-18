import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SeriesService} from "./series.service";
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuario';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  template: `
    <header>
      <div class="barra-superior">
        <div class="logo">
          <a [routerLink]="['/']">
          <img src="assets/images/polaflix.png" alt="Logo">
          </a>
        </div>
        <div class="usuario">
          <span>Bienvenido {{ user?.nombre }}</span>
          <img src="assets/images/user.png" alt="Usuario">
        </div>
      </div>
      <nav class="menu">
        <a [routerLink]="['/']">Inicio</a>
        <a [routerLink]="['/']">Agregar Serie</a>
        <a href="#">Ver Cargos</a>
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

  //User var 
  userId: number = 1; // This can be dynamic based on the logged-in user
  user: Usuario | undefined;

  constructor(private seriesService: SeriesService, private usuarioService: UsuarioService) {
    this.usuarioService.getUsuarioById(this.userId).then(user => {
      this.user = user;
      console.log(this.user);
    });
    this.seriesService.getUserSeriesPendientes().then(console.log);
    this.seriesService.getUserSeriesEmpezadas().then(console.log);
    this.seriesService.getUserSeriesTerminadas().then(console.log);
  }
}
