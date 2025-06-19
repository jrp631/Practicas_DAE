import { Component, inject, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Serie } from '../serie';
import { SeriesService } from '../series.service';

@Component({
    selector: 'app-serie',
    standalone: true,
    imports: [RouterModule],
    template: `
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
