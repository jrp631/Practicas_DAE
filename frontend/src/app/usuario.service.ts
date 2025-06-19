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
        if (!data.ok) {
            throw new Error('Error al obtener el usuario');
        }
        return (await data.json()) ?? {};
    }
}
