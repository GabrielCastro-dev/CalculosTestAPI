package calculo.app.service;

import calculo.app.entity.Entrada;
import calculo.app.entity.Saida;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CalculoServiceTest {
    @Autowired
    CalculoService calculoService;

    // TESTES UNITÁRIOS

    @Test
    void canario01() {
        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(5);
        lista.add(17);
        lista.add(1);
        // A soma dos números acima deve resultar em 25
        // caso contrário, o teste falha

        Integer retorno =  this.calculoService.somar(lista);

        // Teste de fato abaixo
        assertEquals(25, retorno);
    }

//    @Test
//    void canario02() {
//        // Cenário que pressupõe número nulo lista.
//        // O caso deve ser previsto e tratado em CalculoService
//        List<Integer> lista = new ArrayList<>();
//        lista.add(2);
//        lista.add(5);
//        lista.add(null);
//        lista.add(1);
//
//        Integer retorno =  this.calculoService.somar(lista);
//
//        // Teste de fato abaixo
//        assertEquals(8, retorno);
//    }

    @Test
    void canario02() {
        // Cenário que pressupõe número nulo lista.
        // Neste caso a soma não deve ocorrer, e um erro é lançado
        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(5);
        lista.add(null);
        lista.add(1);

        // Em caso de exceção, passa no teste
        assertThrows(Exception.class, () -> {
            Integer retorno = this.calculoService.somar(lista);
        });
    }

    // TESTES DE INTEGRAÇÃO, NO MÉTODO CALCULAR()

    @Test
    void integracao01_listaValida() {
        Entrada entrada = new Entrada();
        entrada.setListaNumeros(List.of(2, 5, 17, 1));

        Saida saida = this.calculoService.calcular(entrada);

        assertEquals(25, saida.getSoma());
        assertEquals(17, saida.getMaiorNumeroLista());
    }

    @Test
    void integracao02_listaNula() {
        Entrada entrada = new Entrada();
        entrada.setListaNumeros(null);

        assertThrows(IllegalArgumentException.class, () -> {
            this.calculoService.calcular(entrada);
        });
    }

    @Test
    void integracao03_listaVazia() {
        Entrada entrada = new Entrada();
        entrada.setListaNumeros(List.of());

        assertThrows(IllegalArgumentException.class, () -> {
            this.calculoService.calcular(entrada);
        });
    }

    @Test
    void integracao04_listaComValorNulo() {
        Entrada entrada = new Entrada();

        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(null);
        lista.add(3);

        entrada.setListaNumeros(lista);

        assertThrows(NullPointerException.class, () -> {
            this.calculoService.calcular(entrada);
        });
    }
}
