package calculo.app.service;

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

}
