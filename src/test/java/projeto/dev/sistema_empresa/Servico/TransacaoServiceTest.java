package projeto.dev.sistema_empresa.Servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import org.springframework.web.client.RestTemplate;

import projeto.dev.sistema_empresa.Modelo.Cliente;
import projeto.dev.sistema_empresa.Modelo.Empresa;
import projeto.dev.sistema_empresa.Modelo.RequisicaoWebhook;
import projeto.dev.sistema_empresa.Modelo.TipoTransacao;
import projeto.dev.sistema_empresa.Modelo.Transacao;
import projeto.dev.sistema_empresa.Repositorios.RepositorioCliente;
import projeto.dev.sistema_empresa.Repositorios.RepositorioEmpresa;
import projeto.dev.sistema_empresa.Repositorios.RepositorioTransacao;
import projeto.dev.sistema_empresa.Servicos.TransacaoService;

public class TransacaoServiceTest {

    @Mock
    private RepositorioCliente clienteRepository;

    @Mock
    private RepositorioEmpresa empresaRepository;

    @Mock
    private RepositorioTransacao transacaoRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TransacaoService transacaoService;

    public TransacaoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testarRealizarTransacao_SucessoComDeposito() {
        // Configurações
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("123.456.789-00");
        cliente.setNome("Cliente Teste"); // Adicione o nome do cliente se disponível

        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNome("Empresa Teste");
        empresa.setCnpj("12.345.678/0001-95");
        empresa.setSaldo(BigDecimal.valueOf(1000));
        empresa.setTaxaSistema(BigDecimal.valueOf(10));

        Transacao transacao = new Transacao();
        transacao.setClienteId(cliente.getId());
        transacao.setEmpresaId(empresa.getId());
        transacao.setValor(BigDecimal.valueOf(100));
        transacao.setDataHora(LocalDateTime.now());

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));
        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        // Simula a chamada ao RestTemplate
        when(restTemplate.postForObject(anyString(), any(), eq(String.class))).thenReturn("Success");

        // Chama o método a ser testado
        transacaoService.realizarTransacao(1L, 1L, BigDecimal.valueOf(100), TipoTransacao.DEPOSITO);

        // Captura o argumento passado para postForObject
        ArgumentCaptor<RequisicaoWebhook> requisicaoCaptor = ArgumentCaptor.forClass(RequisicaoWebhook.class);
        verify(restTemplate).postForObject(
            eq("https://webhook.site/18b055c5-ef0b-4f3b-b140-e6f8d0944c21"),
            requisicaoCaptor.capture(),
            eq(String.class)
        );

        RequisicaoWebhook requisicaoCapturada = requisicaoCaptor.getValue();
        assertEquals(empresa.getId(), requisicaoCapturada.getId());
        assertEquals(empresa.getNome(), requisicaoCapturada.getNome());
        assertEquals(empresa.getCnpj(), requisicaoCapturada.getCnpj());
        assertEquals(empresa.getSaldo(), requisicaoCapturada.getSaldo());
        assertEquals(empresa.getTaxaSistema(), requisicaoCapturada.getTaxaSistema());
    }
}
