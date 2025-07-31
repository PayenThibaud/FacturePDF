import { Routes } from '@angular/router';
import { FactureComponent } from './facture/facture';

export const routes: Routes = [
  { path: 'facture', component: FactureComponent },
  { path: '', redirectTo: 'facture', pathMatch: 'full' }
];