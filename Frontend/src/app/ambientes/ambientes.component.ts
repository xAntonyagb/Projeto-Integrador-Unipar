import { Component } from '@angular/core';

@Component({
  selector: 'app-ambientes',
  templateUrl: './ambientes.component.html',
  styleUrls: ['./ambientes.component.scss']
})
export class AmbientesComponent {
  pageTitles: string[] = ['AMBIENTES', 'BLOCOS', 'LANÇAMENTOS'];
  currentPageTitle: string = this.pageTitles[0];

  updatePageTitle(index: number) {
    this.currentPageTitle = this.pageTitles[index];
  }
}
