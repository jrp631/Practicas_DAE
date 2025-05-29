import { Component, inject, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SerieComponent } from '../serie/serie.component';
import { SeriesService } from '../series.service';
import { Serie } from '../serie';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-serie-usuario',
    imports: [RouterModule, SerieComponent, CommonModule],
    template: `
        <section>
            <h2 class="titulo_lista_peliculas">Empezadas</h2>
            <div class="scroll-container">
                <!-- // llamar a serie -->
                <app-serie 
                    *ngFor="let serie of seriesEmpezadas"
                    [serie]="serie"
                ></app-serie>
            </div>
        </section>
        <hr>
        <section>
            <h2 class="titulo_lista_peliculas">Pendientes</h2>
            <div class="scroll-container">
                <!-- // llamar a serie -->
                <app-serie
                    *ngFor="let serie of seriesPendientes"
                    [serie]="serie"
                ></app-serie>
            </div>
        </section>
        <hr>
        <section>
            <h2 class="titulo_lista_peliculas">Terminadas</h2>
            <div class="scroll-container">
                <!-- // llamar a serie -->
                <app-serie
                    *ngFor="let serie of seriesTerminadas"
                    [serie]="serie"
                ></app-serie>
            </div>
        </section>

    `,
    styleUrls: ['./series-usuario.component.css'],

})

export class SeriesUsuarioComponent {
    seriesPendientes: Serie[] = []; // Array de series pendientes inicializado por el constructor
    seriesTerminadas: Serie[] = []; // Array de series terminadas inicializado por el constructor
    seriesEmpezadas: Serie[] = []; // Array de series empezadas inicializado por el constructor
    seriesService : SeriesService = inject(SeriesService);

    seriesPendientesFiltered: Serie[] = []; // Array de series pendientes filtradas inicializado por el constructor
    constructor() {

        // async call to the service to get the list of seriesPendientes
        this.seriesService.getUserSeriesPendientes().then((seriesPendientes: Serie[]) => {
            this.seriesPendientes = seriesPendientes;
            // this.convertName(this.seriesPendientes);
            // console.log("hola");
            // console.log(this.seriesPendientes);
        });

        this.seriesService.getUserSeriesTerminadas().then((seriesTerminadas: Serie[]) => {
            this.seriesTerminadas = seriesTerminadas;
        });

        this.seriesService.getUserSeriesEmpezadas().then((seriesEmpezadas: Serie[]) => {
            this.seriesEmpezadas = seriesEmpezadas;
        });
        
    }

    // convertName(s: Serie[]) {
    //     // Convertir el nombre de la serie a minusculas
    //     s.forEach((serie) => {
    //         serie.nombreSerie = serie.nombreSerie.toLowerCase();
    //         // change spaces to underscores
    //         serie.nombreSerie = serie.nombreSerie.replace(/ /g, '_');
    //     });

    // }


}