import { Component, Input } from "@angular/core";
import { RouterModule } from "@angular/router";
import { Usuario } from "../usuario";
import { UsuarioService } from "../usuario.service";
import { userData } from "../environments/environments";


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
                    <a [routerLink]="['/ver-cargos', userId]">Ver Cargos</a>
                </nav>
                </header>   
    `,
    styleUrls: ["./menu-navegacion.component.css"]
})
export class MenuNavegacionComponent {
    // @Input() user?: Usuario;
    user: Usuario | undefined;
    userId: number = userData.userId; // This can be dynamic based on the logged-in user
    constructor(private usuarioService: UsuarioService) {}

    async ngOnInit() {
        try {
            this.user = await this.usuarioService.getUsuarioById(userData.userId);
        } catch (error) {
            // Aquí puedes manejar el error, por ejemplo mostrar un mensaje
            console.error('Error al obtener el usuario', error);
        }
    }

}