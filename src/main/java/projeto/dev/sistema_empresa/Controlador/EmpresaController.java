package projeto.dev.sistema_empresa.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.dev.sistema_empresa.Modelo.Empresa;
import projeto.dev.sistema_empresa.Repositorios.RepositorioEmpresa;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private RepositorioEmpresa empresaRepository;

    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaRepository.save(empresa);
        return new ResponseEntity<>(novaEmpresa, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresa(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(empresa -> new ResponseEntity<>(empresa, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
