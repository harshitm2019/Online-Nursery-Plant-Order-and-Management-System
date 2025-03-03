import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'addOneYear',
  standalone: true
})
export class AddOneYearPipe implements PipeTransform {

  transform(value : Date): any {
    
     const currentDate = new Date(value);

     currentDate.setFullYear(currentDate.getFullYear() + 1);

     return currentDate as any;

  }

}
