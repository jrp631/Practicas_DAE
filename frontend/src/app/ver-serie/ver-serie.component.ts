import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Serie } from '../serie';
import { SeriesService } from '../series.service';
import { CapituloComponent } from "../capitulo/capitulo.component";
import { Capitulo } from '../capitulo';

@Component({
    selector: 'app-ver-serie',
    imports: [CommonModule, ReactiveFormsModule, CapituloComponent],
    template: `
    <div class="titulo-serie">
      <h1 >{{ serie?.nombreSerie }}</h1>
      <span *ngIf="serie?.esCategoria"
            [ngClass]="{
              'gold-badge': serie?.esCategoria === 'GOLD',
              'silver-badge': serie?.esCategoria === 'SILVER',
              'estandar-badge': serie?.esCategoria === 'STANDARD'
            }">
        {{ serie?.esCategoria }}
      </span>
    </div>
    <!-- <p>Temporadas: {{ serie?.numeroTemporadas }}</p> -->

    <div class="temporadas">
        <button
            *ngFor="let temporada of temporadas; let i = index"
            [ngClass]="{
                'temporada-btn': true,
                'temporada-btn-selected': temporadaActual === temporada
            }"
            (click)="temporadaActual = temporada"
        >
            Temporada {{ temporada }}
        </button>
    </div>

    <div class="episodios">
    <app-capitulo
        *ngFor="let capitulo of capitulosPorTemporada[temporadaActual - 1]"
        [capitulo]="capitulo" 
        [serieId]="serie?.idSerie ?? ''"
    ></app-capitulo>
    </div>
    `,
    styleUrls: ['./ver-serie.component.css'],
})
export class VerSerieComponent {
    route: ActivatedRoute = inject(ActivatedRoute);
    seriesService: SeriesService = inject(SeriesService);
    temporadaActual: number = 1; // Temporada actual, se puede cambiar según la lógica de la aplicación
    temporadas: number[] = []; // Lista de temporadas para el ngFor
    serie: Serie | undefined;
    serieId: number | undefined;

    capitulos: Capitulo[] | undefined;
    capitulosPorTemporada: Capitulo[][] = [];

    constructor() {
        console.log("VerSerieComponent initialized");
        let serieId = this.route.snapshot.params['id'];
        console.log("Route params:", this.route.params);
        (async () => {
            try {
                const serie = await this.seriesService.getSerieById(serieId);
                this.serie = serie;
                const numTemporadas = parseInt(serie.numeroTemporadas, 10);
                this.temporadas = Array.from({ length: numTemporadas }, (_, i) => i + 1);

                this.capitulos = await this.seriesService.getCapitulosBySerieId(serieId);
                console.log('Capítulos obtenidos:', this.capitulos);

                for (let i = 1; i <= numTemporadas; i++) {
                    try {
                        const capitulosTemporada = await this.seriesService.getCapitulosByTemporada(parseInt(serieId, 10), i);
                        this.capitulosPorTemporada[i - 1] = capitulosTemporada;
                        console.log(`Capítulos de la temporada ${i}:`, capitulosTemporada);
                    } catch (error) {
                        console.error(`Error al obtener capítulos de la temporada ${i}:`, error);
                    }
                }
            } catch (error) {
                console.error('Error al obtener la serie o capítulos:', error);
            }
        })();
    }

    ngOnInit() {
        //TODO

        // get the serie by id from the route params
    }

    // get number of seasons 

    // get chapters of a season
    async getCapitulosOfSelectedTemporada() {
        if (!this.serieId || !this.temporadaActual) return;
        console.log(`Obteniendo capítulos de la temporada ${this.temporadaActual} de la serie ${this.serieId}`);
        try {
            const capitulos = await this.seriesService.getCapitulosByTemporada(this.serieId, this.temporadaActual);
            this.capitulosPorTemporada[this.temporadaActual - 1] = capitulos;
        } catch (error) {
            console.error('Error al obtener capítulos de la temporada seleccionada:', error);
        }
    }
}
