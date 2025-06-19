import { Component, Input } from "@angular/core";
import { RouterModule } from "@angular/router";
import { Usuario } from "../usuario";
import { UsuarioService } from "../usuario.service";

@Component({
    selector: "app-menu-navegacion",
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
    `,
    styleUrls: ["./menu-navegacion.component.css"]
})
export class MenuNavegacionComponent {
    // @Input() user?: Usuario;
    user: Usuario | undefined;
    userId: number = 1; // This can be dynamic based on the logged-in user
    constructor(private usuarioService: UsuarioService) {
        this.usuarioService.getUsuarioById(this.userId).then(user => {
            this.user = user;
        });
    }

}