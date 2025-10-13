package calculo.app.controller;

import calculo.app.entity.Entrada;
import calculo.app.entity.Saida;
import calculo.app.service.CalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/calculo")
public class CalculoController {

    @Autowired
    private CalculoService calculoService;

    @PostMapping
    public ResponseEntity<?> calcular(@RequestBody Entrada entrada) {
        try {
            Saida saida = this.calculoService.calcular(entrada);
            return ResponseEntity.ok(saida);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

}
