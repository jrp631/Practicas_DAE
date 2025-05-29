import {Injectable} from '@angular/core';
import { Usuario  } from './usuario';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {
    readonly url = 'http://localhost:8080';

    constructor() { }

    async getUsuarioById(id: number): Promise<Usuario> {
        const data = await fetch(`${this.url}/usuarios/${id}`);
        return (await data.json()) ?? {};
    }
}
