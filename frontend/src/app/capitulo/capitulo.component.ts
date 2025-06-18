import { Component, inject, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Serie } from '../serie';
import { Capitulo } from '../capitulo';
import { CapituloService } from '../capitulo.service';
import { c } from '@angular/core/event_dispatcher.d-pVP0-wST';

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
            <p [class.visto]="capituloVisto" [class.pendiente]="!capituloVisto">
                {{ capituloVisto ? 'Visto' : 'Pendiente' }}
            </p>
            <br />
            
        </div>
        <div class="capitulo-descripcion">
        <!-- <div> -->
            <p>{{ capitulo.descripcion }}</p>
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

        // const videoPath = 'assets/videos/videoplayback.mp4';
        // const videoElement = document.createElement('video');
        // videoElement.src = videoPath;
        // videoElement.controls = true;
        // videoElement.autoplay = true;
        // videoElement.className = 'capitulo-video';

        // // Create a close button
        // const closeButton = document.createElement('button');
        // closeButton.textContent = 'Cerrar';
        // closeButton.className = 'capitulo-video-close';

        // // Container for video and button
        // const container = document.createElement('div');
        // container.className = 'capitulo-video-container';

        // closeButton.onclick = () => {
        //     container.remove();
        // };

        // container.appendChild(videoElement);
        // container.appendChild(closeButton);
        // document.body.appendChild(container);
        this.capituloService.putVerCapitulo("1",this.serieId, this.capitulo.idCapitulo);
        this.checkCapituloVisto();
    }

    // method to check if the chapter has been seen
    async checkCapituloVisto() {
        try {
            this.capituloVisto = await this.capituloService.getVerCapitulo("1", this.serieId, this.capitulo.idCapitulo);
            console.log(`Capítulo ${this.capitulo.idCapitulo} visto: ${this.capituloVisto}`);
        } catch (error) {
            console.error('Error checking chapter status:', error);
        }
    }
}