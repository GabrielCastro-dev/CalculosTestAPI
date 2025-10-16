package calculo.app.controller;

import calculo.app.entity.Entrada;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CalculoControllerTest {
    @Autowired
    CalculoController calculoController;

    @Test
    void cenario01(){
        // Teste de integração sem Mock
        // Configura-se como teste de integração pois chama outro método,
        // neste caso o calcular do CalculoService

        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(5);
        lista.add(17);
        lista.add(1);

        Entrada entrada = new Entrada();
        entrada.setListaNumeros(lista);

        ResponseEntity<?> retorno = this.calculoController.calcular(entrada);

        // Testa com base no status da resposta
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void cenario02(){
        // Teste de integração sem Mock
        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(5);
        lista.add(null);
        lista.add(1);

        Entrada entrada = new Entrada();
        entrada.setListaNumeros(lista);

        // Espera que de erro, já que temos um número nulo na lista nesse caso
        assertThrows(Exception.class, () -> {
            ResponseEntity<?> retorno = this.calculoController.calcular(entrada);
        });
    }
}
