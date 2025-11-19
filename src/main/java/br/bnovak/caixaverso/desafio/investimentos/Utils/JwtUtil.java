package br.bnovak.caixaverso.desafio.investimentos.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static final long NOW_MILLIS =  (30 * 60 * 1000);
    private static final long TEMPO_EXPIRACAO = System.currentTimeMillis() + NOW_MILLIS;

    /**
     * Gera um token JWT para o usuário informado.
     *
     * @param canal o nome do canal de autenticação
     * @return token JWT gerado
     */
    public static String generateToken(String canal) {
        return Jwts.builder()
                .setSubject(canal)
                .setIssuer("caixaverso-api")
                .setIssuedAt(new Date(NOW_MILLIS))
                .setExpiration(new Date(TEMPO_EXPIRACAO))
                .signWith(carregarChavePrivada(), SignatureAlgorithm.RS256)
                .compact();
    }

    private static PrivateKey carregarChavePrivada() {
        try (InputStream is = JwtUtil.class.getResourceAsStream("/privateKey.pem")) {
            if (is == null) {
                throw new RuntimeException("Arquivo privateKey.pem não encontrado");
            }

            String pem = new String(is.readAllBytes(), StandardCharsets.UTF_8)
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(pem);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar chave privada: "
                    + e.getMessage(), e);
        }
    }


}
