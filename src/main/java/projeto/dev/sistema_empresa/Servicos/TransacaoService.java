package projeto.dev.sistema_empresa.Servicos;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import projeto.dev.sistema_empresa.Modelo.Cliente;
import projeto.dev.sistema_empresa.Modelo.Empresa;
import projeto.dev.sistema_empresa.Modelo.RequisicaoWebhook;
import projeto.dev.sistema_empresa.Modelo.TipoTransacao;
import projeto.dev.sistema_empresa.Modelo.Transacao;
import projeto.dev.sistema_empresa.Repositorios.RepositorioCliente;
import projeto.dev.sistema_empresa.Repositorios.RepositorioEmpresa;
import projeto.dev.sistema_empresa.Repositorios.RepositorioTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransacaoService {

    private final RepositorioCliente clienteRepository;
    private final RepositorioEmpresa empresaRepository;
    private final RepositorioTransacao transacaoRepository;
    private final RestTemplate restTemplate;

    public TransacaoService(RepositorioCliente clienteRepository, RepositorioEmpresa empresaRepository,
                            RepositorioTransacao transacaoRepository, RestTemplate restTemplate) {
        this.clienteRepository = clienteRepository;
        this.empresaRepository = empresaRepository;
        this.transacaoRepository = transacaoRepository;
        this.restTemplate = restTemplate;
    }

    public void realizarTransacao(Long clienteId, Long empresaId, BigDecimal valor, TipoTransacao tipo) {
        @SuppressWarnings("unused")
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow();
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow();
    
        // Atualiza o saldo da empresa baseado no tipo de transação
        if (tipo == TipoTransacao.DEPOSITO) {
            empresa.setSaldo(empresa.getSaldo().add(valor));
        } else if (tipo == TipoTransacao.SAQUE) {
            empresa.setSaldo(empresa.getSaldo().subtract(valor));
        }
    
        // Salva a transação e atualiza a empresa com o saldo modificado
        transacaoRepository.save(new Transacao(clienteId, empresaId, valor, LocalDateTime.now()));
        empresaRepository.save(empresa); // Salva a empresa com o saldo atualizado

        enviarCallbackParaEmpresa(empresaId, new Transacao(clienteId, empresaId, valor, LocalDateTime.now()));
    }
    
    public void notificarCliente(Long clienteId, Transacao transacao) {
        System.out.println("Notificando cliente com ID " + clienteId + " sobre a transação: " + transacao);
    }

    public void enviarCallbackParaEmpresa(Long empresaId, Transacao transacao) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow();
        RequisicaoWebhook requisicao = new RequisicaoWebhook(
            empresa.getId(),
            empresa.getNome(),
            empresa.getCnpj(),
            empresa.getSaldo(),
            empresa.getTaxaSistema()
        );
    
        restTemplate.postForObject(
            "https://webhook.site/18b055c5-ef0b-4f3b-b140-e6f8d0944c21",
            requisicao,
            String.class
        );
    }
    
}