import {Pipe, PipeTransform} from '@angular/core';
import {DatePipe} from '@angular/common';

@Pipe({
  name: 'customDate'
})
export class CustomDatePipe extends DatePipe implements PipeTransform {
  transform(value: any, args?: any): any {
    return super.transform(value, 'yyyy/MM/dd');
  }
}

@Pipe({
  name: 'customCpf'
})
export class CustomCpfPipe implements PipeTransform {
  transform(value: any, args?: any): any {
    return value.replace(/(\d{ 3 })(\d{ 3 })(\d{ 3 })(\d{ 2 })/, "$1.$2.$3-$4");
  }
}
