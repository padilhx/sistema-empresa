package projeto.dev.sistema_empresa.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import projeto.dev.sistema_empresa.Modelo.Transacao;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarNotificacao(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(assunto);
        mailMessage.setText(mensagem);
        emailSender.send(mailMessage);
    }

    public void enviarNotificacao(Long clienteId, Transacao transacao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enviarNotificacao'");
    }
}
