package projeto.dev.sistema_empresa.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.dev.sistema_empresa.Modelo.Cliente;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Long> {
}
