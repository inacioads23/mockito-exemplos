package mockito_exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Teste da classe {@link ServicoEnvioEmail} exemplificando testes usando
 * Argument Captor
 */
@ExtendWith(MockitoExtension.class)
public class ServicoEnvioEmailTeste {

	@Mock
	private PlataformaDeEnvio plataforma;

	@InjectMocks // plataforma de teste
	private ServicoEnvioEmail servico;

	@Captor // Captura argumento de um método
	private ArgumentCaptor<Email> emailCaptor;

	@Test
	public void validaSeEmailEstaComDadosCorretos() {

		String email = "jose.alve@provedor.com";
		String mensagem = "Mensagem de Teste 123";

		servico.enviaEmail(email, mensagem, true);
		Mockito.verify(plataforma).enviaEmail(emailCaptor.capture());

		Email emailCapturado = emailCaptor.getValue();		
		Assertions.assertEquals(email, emailCapturado.getEnderecoEmail());
		Assertions.assertEquals(mensagem, emailCapturado.getMensagem());
		Assertions.assertEquals(Formato.HTML, emailCapturado.getFormato());
	}

}
