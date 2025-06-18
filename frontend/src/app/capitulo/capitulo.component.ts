import { Component, inject, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Serie } from '../serie';
import { Capitulo } from '../capitulo';
import { CapituloService } from '../capitulo.service';
import { c } from '@angular/core/event_dispatcher.d-pVP0-wST';
import { Usuario } from '../usuario';
import { DescripcionCapituloComponent } from '../descripcion-capitulo/descripcion-capitulo.component';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-capitulo',
    imports: [RouterModule, DescripcionCapituloComponent, CommonModule],
    template: `
        <div class="episodio" (click)="toggleDescripcion()">
            <strong>Ep.{{capitulo.numeroCapitulo}} {{ capitulo.titulo }}</strong>
            <!-- <p>{{ capitulo.descripcion }}</p> -->
            <a class="verCapitulo" (click)="verCapitulo()"> 
                Ver
            </a>
            <p [class.visto]="capituloVisto" [class.pendiente]="!capituloVisto">
                {{ capituloVisto ? 'Visto' : 'Pendiente' }}
            </p>
            <br />
        </div>
        <app-descripcion-capitulo 
            *ngIf="mostrarDescripcion" 
            [descripcion]="capitulo.descripcion">
        </app-descripcion-capitulo>
    `,
    styleUrls: ['./capitulo.component.css'],
})
export class CapituloComponent {
    @Input() capitulo!: Capitulo;
    @Input() serieId!: String;
    @Input() categoria!: string;
    @Input() usuario!: Usuario;
    capituloService = inject(CapituloService);
    capituloVisto : Boolean = false;
    mostrarDescripcion = false;
    mostrarDescripcionPrevState = false;

    ngOnChanges() {
        if (this.serieId && this.capitulo) {
            this.checkCapituloVisto();
        }
    }

    verCapitulo() {
        // event.stopPropagation();
        this.capituloService.putVerCapitulo("1",this.serieId, this.capitulo.idCapitulo);
        this.checkCapituloVisto();
    }

    toggleDescripcion() {
        this.mostrarDescripcion = !this.mostrarDescripcion;
        console.log("Ver descripcion")
    }

    async checkCapituloVisto() {
        try {
            this.capituloVisto = await this.capituloService.getVerCapitulo("1", this.serieId, this.capitulo.idCapitulo);
            console.log(`Capítulo ${this.capitulo.idCapitulo} visto: ${this.capituloVisto}`);
        } catch (error) {
            console.error('Error checking chapter status:', error);
        }
    }
}
