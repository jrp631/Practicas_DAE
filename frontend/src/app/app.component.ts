import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SeriesService} from "./series.service";
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuario';
import { MenuNavegacionComponent } from "./menu-navegacion/menu-navegacion.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, MenuNavegacionComponent],
  template: `

     <app-menu-navegacion></app-menu-navegacion>

    <router-outlet></router-outlet>
    <!-- <app-serie-usuario></app-serie-usuario> -->
    <!-- <app-inicio></app-inicio> -->
  `,
  styleUrls: ['./app.component.css'],
})
export class AppComponent {

}
