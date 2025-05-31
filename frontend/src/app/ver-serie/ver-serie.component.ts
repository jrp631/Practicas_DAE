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
            *ngFor="let temporada of [].constructor(serie?.numeroTemporadas || 0); let i = index"
            [ngClass]="{
                'temporada-btn': true,
                'temporada-btn-selected': temporadaActual === i + 1
            }"
            (click)="temporadaActual = i + 1"
        >
            Temporada {{ i + 1 }}
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
    temporadas: number = 1; // Número total de temporadas, se puede cambiar según la lógica de la aplicación
    serie: Serie | undefined;
    //@Input() serie!: Serie; // Use ! to assert that serie will be defined after the async call
   
   
    capitulos: Capitulo[] | undefined;
    capitulosPorTemporada: Capitulo[][] = [];

    constructor() {
        let serieId = this.route.snapshot.params['id'];
        console.log("Route params:", this.route.params);
        this.seriesService.getSerieById(serieId).then((serie: Serie) => {
            this.serie = serie;
            console.log('Serie obtenida:', this.serie);
            this.temporadas = parseInt(serie.numeroTemporadas, 10);

            //  get the capitulos of the serie
            this.seriesService.getCapitulosBySerieId(serieId).then((capitulos: Capitulo[]) => {
                this.capitulos = capitulos;
                console.log('Capítulos obtenidos:', this.capitulos);
            });

            // get the capitulos of the temporada
            for (let i = 1; i <= this.temporadas; i++) {
                this.seriesService.getCapitulosByTemporada(parseInt(serieId, 10), i).then((capitulos: Capitulo[]) => {
                    this.capitulosPorTemporada[i - 1] = capitulos;
                    console.log(`Capítulos de la temporada ${i}:`, capitulos);
                });
            }
        });


    }

    // get number of seasons 

    // get chapters of a season
}
