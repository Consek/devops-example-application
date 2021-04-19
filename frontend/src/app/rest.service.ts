import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class RestService {

  constructor(private http: HttpClient) {}

  getInstances(): Observable<Instance[]> {
    let endpoint = 'http://localhost:8888/instances'//'${BACKEND_URL}/instances'
    return this.http.get<Instance[]>(endpoint).pipe(map(result => <Instance[]>result));
  }
}


export interface Instance {
  hostname: string;
  version: string;
  isProxy: boolean;
  isActive: boolean;
}
