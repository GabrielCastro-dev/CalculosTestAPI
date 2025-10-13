package calculo.app.service;

import calculo.app.entity.Entrada;
import calculo.app.entity.Saida;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculoService {

    public Saida calcular(Entrada entrada){
        List<Integer> lista = entrada.getListaNumeros();

        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
        }

        Saida saida = new Saida();
        saida.setSoma(this.somar(lista));
        saida.setMaiorNumeroLista(this.buscarMaiorNumero(lista));

        return saida;
    }

    public int somar(List<Integer> lista){
        int total = 0;

        for (int i = 0; i < lista.size(); i++){
            total += lista.get(i);
        }

        return total;
    }

    public int buscarMaiorNumero(List<Integer> lista){
        int maior = lista.get(0);

        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i) > maior) {
                maior = lista.get(i);
            }
        }

        return maior;
    }
}
