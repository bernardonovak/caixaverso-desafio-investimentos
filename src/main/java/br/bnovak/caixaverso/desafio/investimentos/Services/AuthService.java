package br.bnovak.caixaverso.desafio.investimentos.Services;

import br.bnovak.caixaverso.desafio.investimentos.Exceptions.NaoAutorizadoException;
import br.bnovak.caixaverso.desafio.investimentos.Utils.JwtUtil;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class AuthService {

    private static final String CANAL_ESPERADO = "caixaverso";
    private static final String CHAVE_ESPERADA = "LS0tLS1CRUdJTiBPUEVOU1NIIFBSSVZBVEUgS0VZLS0tLS0KYjNCbGJuTnphQzFyWlhrdGRqRUFBQUFBQ21GbGN6STFOaTFqZEhJQUFBQUdZbU55ZVhCMEFBQUFHQUFBQUJENWt1QnpxUgpZUGNQWmlmZ3YzUC9TTkFBQUFHQUFBQUFFQUFBQXpBQUFBQzNOemFDMWxaREkxTlRFNUFBQUFJQjhEeUZ5dlVtMFdWL3p3CkJzUVpnTUM1bUhRZ05KOVlVVGpFVW4rZENDWWpBQUFBb0dhVXRwUEV2cXRvMWpqVkxqNGd2OHppL1RzRTFMbHJVSGhpYkMKMU1ISjVaTGtKa3g4VG1WUi95R2NSNGxUSWlMS0xJYVJrQ0V3MDR3TVI1emtCblVsNVpwbzRsT3JlNTIrZmdGR0xrandqSApKMWxZZzFVd3BNOUl2aENRUHp6WHhKNnJoL1FIdlNMcmRXaGxvek0yNm5EdnZ3R0w2TlZVdWZjN1RJNSt2b1MzcnhoL2lDCmQrYWprWlViVVh1WE51VG5RTWhnak1YTnYyM1pWcnAzVU9qRWM9Ci0tLS0tRU5EIE9QRU5TU0ggUFJJVkFURSBLRVktLS0tLQo=";

    public Map<String, Object> gerarToken(String canal, String chave) throws NaoAutorizadoException{
        if (!CANAL_ESPERADO.equals(canal) || !CHAVE_ESPERADA.equals(chave)) {
            throw new NaoAutorizadoException("Acesso negado");
        }

        String token = JwtUtil.generateToken(canal);
        return Map.of(
                "access_token", token,
                "token_type", "Bearer",
                "expires_in", 1800 // segundos (30 minutos)
        );
    }
}
