import { Component, Input } from "@angular/core";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";

@Component({
    selector: 'app-descripcion-capitulo',
    imports: [RouterModule, CommonModule],
    template: `
        <div class="descripcion-capitulo" [class.hidden]="!visible" *ngIf="visible">
            <p>{{ descripcion }}</p>
            <button (click)="toggleVisibility()">🔼</button>
        </div>  
    `,
    styleUrls: ['./descripcion-capitulo.component.css'],
})
export class DescripcionCapituloComponent {
    @Input() descripcion!: string;
    visible: boolean = true;

    toggleVisibility() {
        this.visible = !this.visible;
    }
}