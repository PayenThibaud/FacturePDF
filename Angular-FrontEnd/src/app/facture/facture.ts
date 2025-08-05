import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-facture',
  standalone: true,
  imports: [HttpClientModule, FormsModule, CommonModule],
  templateUrl: './facture.html'
})
export class FactureComponent {
  facture = {
     clientNom: '',
  date: '',
  montant: 0,
  clienttelephone: '',
  clientemail: '',
  clientadresse: ''
  };

  constructor(private http: HttpClient) { }

genererFacture() {
  this.http.post('http://host.docker.internal:8080/api/factures', this.facture, {
    responseType: 'blob'
  }).subscribe({
    next: (blob) => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'facture.pdf';
      a.click();
      window.URL.revokeObjectURL(url);
    },
    error: (err) => {
      const reader = new FileReader();
      reader.onload = () => {
        console.error('🛑 Erreur backend :', reader.result);
        alert('Erreur lors de la génération :\n' + reader.result);
      };
      if (err.error instanceof Blob) {
        reader.readAsText(err.error);
      } else {
        console.error('Erreur inattendue :', err);
        alert('Erreur inconnue : ' + err.message);
      }
    }
  });
}

buttonClicked = false;

arreterBackend() {
  this.buttonClicked = true;
  this.http.post('http://host.docker.internal:8080/api/eteindre', {}).subscribe({
    next: () => alert('Backend arrêté !'),
    error: err => alert('Erreur lors de l\'arrêt du backend : ' + err.message)
  });
}
}
