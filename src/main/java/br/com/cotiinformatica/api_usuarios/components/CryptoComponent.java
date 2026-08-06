package br.com.cotiinformatica.api_usuarios.components;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.springframework.stereotype.Component;

@Component
public class CryptoComponent {

    /*
     * Criptografa uma senha e retorna seu hash SHA-256.
     */
    public String sha256(String senha) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(
                    senha.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "Algoritmo SHA-256 não disponível.",
                    e
            );
        }
    }
}