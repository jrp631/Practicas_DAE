import { Component, inject, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Serie } from '../serie';
import { SeriesService } from '../series.service';

@Component({
    selector: 'app-serie',
    standalone: true,
    imports: [RouterModule],
    template: `
        <!-- <img src="assets/images/series/house_of_cards.jpg" alt="House of Cards">
        <img src="assets/images/series/modern_family.jpg" alt="Modern Family">
        <img src="assets/images/series/it_crowd.jpg" alt="IT Crowd">
        <img src="assets/images/series/game_of_thrones.jpg" alt="Game of Thrones">
        <img src="assets/images/series/el_secreto_de_puente_viejo.jpg" alt="El Secreto de Puente Viejo">
        <img src="assets/images/series/cars.jpg" alt="Cars">
        <img src="assets/images/series/cars2.jpg" alt="Cars 2">
        <img src="assets/images/series/cars3.jpg" alt="Cars 3">
        <img src="assets/images/series/rey_leon.jpg" alt="El Rey León"> -->
        <a [routerLink]="['/ver-serie', serie.idSerie]">
        <img [src]="'assets/images/series/' + lowerCaseName(serie) + '.jpg'" >
        </a>
    `,
    styleUrls: ['./serie.component.css'],
})

export class SerieComponent {
    @Input() serie!: Serie;


    lowerCaseName(serie: Serie | undefined)  {
        // Check if serie is defined before accessing its properties
        if (!serie || !serie.nombreSerie) {
            return '';
        }
        // Convertir el nombre de la serie a minúsculas y cambiar espacios por guiones bajos
        let nombreSerie = serie.nombreSerie.toLowerCase().replace(/ /g, '_');
        // console.log(nombreSerie);
        return nombreSerie;
    }

    lowerCaseNameArray(s: Serie[]) {
            // Convertir el nombre de la serie a minusculas
        s.forEach((serie) => {
            serie.nombreSerie = serie.nombreSerie.toLowerCase();
            // change spaces to underscores
            serie.nombreSerie = serie.nombreSerie.replace(/ /g, '_');
        });
    }
}
