import { Injectable } from '@angular/core';
import {Course} from "../model/course";
import {HttpClient} from "@angular/common/http";
import {delay, first, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = 'api/courses'

  constructor(private httpClient: HttpClient) {  }

  list(){
    return this.httpClient.get<Course[]>(this.API)
      .pipe(
        first(),
        // delay(5000),
        )
  }

  loadById(id: string) {
   return this.httpClient.get<Course>(`${this.API}/${id}`)
  }

  save(record: Partial<Course>) {
    if(record._id) {
      return this.update(record)
    }
    return this.create(record)
  }

  private create(record: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, record)
  }

  private update(record: Partial<Course>) {
    return this.httpClient.put<Course>(`${this.API}/${record._id}`, record)
  }

}
