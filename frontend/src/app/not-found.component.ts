import { Component } from '@angular/core';

@Component({
  selector: 'app-not-found',
  standalone: true,
  template: `
    <div class="not-found">
      <h2>Página no encontrada (404)</h2>
      <p>La URL solicitada no existe.</p>
    </div>
  `,
  styles: [`
    .not-found { text-align: center; margin-top: 50px; }
  `]
})
export class NotFoundComponent {}