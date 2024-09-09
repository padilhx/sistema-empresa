package projeto.dev.sistema_empresa.Controlador;

import projeto.dev.sistema_empresa.Modelo.TipoTransacao;
import projeto.dev.sistema_empresa.Servicos.TransacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/realizar")
    public ResponseEntity<String> realizarTransacao(@RequestParam Long clienteId, 
                                                    @RequestParam Long empresaId, 
                                                    @RequestParam BigDecimal valor, 
                                                    @RequestParam TipoTransacao tipo) {
        try {
            transacaoService.realizarTransacao(clienteId, empresaId, valor, tipo);
            return ResponseEntity.ok("Transação realizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar transação: " + e.getMessage());
        }
    }
}
