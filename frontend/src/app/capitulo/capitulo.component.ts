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
            <p>{{ capituloVisto ? 'Visto' : 'Pendiente' }}</p>
        </div>
    `,
    styleUrls: ['./capitulo.component.css'],
})
export class CapituloComponent {
    @Input() capitulo!: Capitulo;
    @Input() serieId!: String;
    @Input() categoria!: string; // ✅
    capituloService = inject(CapituloService);
    capituloVisto : Boolean = false; // Variable to track if the chapter has been seen
    
    ngOnChanges() {
        if (this.serieId && this.capitulo) {
            this.checkCapituloVisto();
        }
    }

    verCapitulo() {
        this.capituloService.putVerCapitulo("1",this.serieId, this.capitulo.idCapitulo);
        this.checkCapituloVisto();
    }

    // method to check if the chapter has been seen
    async checkCapituloVisto() {
        try {
            this.capituloVisto = await this.capituloService.getVerCapitulo("1", this.serieId, this.capitulo.idCapitulo);
        } catch (error) {
            console.error('Error checking chapter status:', error);
        }
    }
}