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
    <p>Placeholder para temporadas</p>
    <div class="episodios">
    <app-capitulo
        *ngFor="let capitulo of capitulos"
        [capitulo]="capitulo" 
        [serieId]="'1'"
    ></app-capitulo>
    </div>
    `,
    styleUrls: ['./ver-serie.component.css'],
})
export class VerSerieComponent {
    route: ActivatedRoute = inject(ActivatedRoute);
    seriesService: SeriesService = inject(SeriesService);

    serie: Serie | undefined;
    //@Input() serie!: Serie; // Use ! to assert that serie will be defined after the async call
   
   
    capitulos: Capitulo[] | undefined;

    constructor() {
        let serieId = this.route.snapshot.params['id'];
        console.log("Route params:", this.route.params);
        this.seriesService.getSerieById(serieId).then((serie: Serie) => {
            this.serie = serie;
            console.log('Serie obtenida:', this.serie);

            //  get the capitulos of the serie
            this.seriesService.getCapitulosBySerieId(serieId).then((capitulos: Capitulo[]) => {
                this.capitulos = capitulos;
                console.log('Capítulos obtenidos:', this.capitulos);
            });
        });


    }

    // get number of seasons 

    // get chapters of a season
}
