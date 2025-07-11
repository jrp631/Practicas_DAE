import { Component, inject, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Serie } from '../serie';
import { Capitulo } from '../capitulo';
import { CapituloService } from '../capitulo.service';
import { c } from '@angular/core/event_dispatcher.d-pVP0-wST';
import { Usuario } from '../usuario';
import { DescripcionCapituloComponent } from '../descripcion-capitulo/descripcion-capitulo.component';
import { CommonModule } from '@angular/common';
import { userData } from '../environments/environments';

@Component({
    selector: 'app-capitulo',
    imports: [RouterModule, DescripcionCapituloComponent, CommonModule],
    template: `
        <!-- <div class="episodio" (click)="toggleDescripcion()"> -->
        <div class="episodio">

            <strong>Ep.{{capitulo.numeroCapitulo}} {{ capitulo.titulo }}</strong>
            <!-- <p>{{ capitulo.descripcion }}</p> -->
            
            <a class="verDescripcion" (click)="toggleDescripcion()"> 
                ℹ️
            </a>

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

    async verCapitulo() {
        try {
            await this.capituloService.putVerCapitulo(userData.userId.toString(),this.serieId, this.capitulo.idCapitulo);
            this.checkCapituloVisto();
        } catch (error) {
            console.error('Error al marcar capítulo como visto:', error);
        }
        
        // this.checkCapituloVisto();
    }

    toggleDescripcion() {
        this.mostrarDescripcion = !this.mostrarDescripcion;
        console.log("Ver descripcion")
    }

    async checkCapituloVisto() {
        try {
            this.capituloVisto = await this.capituloService.getVerCapitulo(userData.userId.toString(), this.serieId, this.capitulo.idCapitulo);
            console.log(`Capítulo ${this.capitulo.idCapitulo} visto: ${this.capituloVisto}`);
        } catch (error) {
            console.error('Error checking chapter status:', error);
        }
    }
}
