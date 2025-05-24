import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SerieComponent } from '../serie/serie.component';
import { SeriesUsuarioComponent } from '../series-usuario/series-usuario.component';

@Component({
    selector: 'app-inicio',
    imports: [CommonModule, SeriesUsuarioComponent],
    template: `
        <main>
            <app-serie-usuario></app-serie-usuario>
            <!-- <hr> -->
            <!-- <app-serie-usuario></app-serie-usuario> -->
            <!-- <hr> -->
            <!-- <app-serie-usuario></app-serie-usuario> -->
            <div class="banner">
                Banner Publicitario
            </div>
        </main>
    `,
    styleUrls: ['./inicio.component.css'],
})

export class InicioComponent {


}