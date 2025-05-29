import { Component, inject, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Serie } from '../serie';
import { Capitulo } from '../capitulo';
import { CapituloService } from '../capitulo.service';

@Component({
    selector: 'app-capitulo',
    imports: [RouterModule],
    template: `
        <div class="episodio">
            <strong>{{ capitulo.titulo }}</strong>
            <!-- <p>{{ capitulo.descripcion }}</p> -->
            <a class="verCapitulo" (click)="verCapitulo()"> 
                Ver
            </a>
            <p>Pendiente | visto</p>
        </div>
    `,
    styleUrls: ['./capitulo.component.css'],
})
export class CapituloComponent {
    @Input() capitulo!: Capitulo;
    @Input() serieId!: String;
    capituloService = inject(CapituloService);

    verCapitulo() {
        this.capituloService.putVerCapitulo("1",this.serieId, this.capitulo.idCapitulo);
    }

    // method to che
}