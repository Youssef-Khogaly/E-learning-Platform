package com.elearning.Videos.temporaryName;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Valid
public class TemporaryNameImpl implements ItemporaryNameService{
    private  final String ALGORITHM = "AES";
    private final byte[] key;
    private String encrypt(String data) throws IllegalBlockSizeException, BadPaddingException {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher encryptCipher = null;
        try{
            encryptCipher = Cipher.getInstance(ALGORITHM);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (InvalidKeyException  | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        byte[] encrypted = encryptCipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted);
    }
    private  OrignalFileName decrypt(String encryptedName) throws IllegalBlockSizeException, BadPaddingException {

        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher decryptCipher = null;
        try{
            decryptCipher = Cipher.getInstance(ALGORITHM);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (InvalidKeyException  | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        byte[] decoded = Base64.getUrlDecoder().decode(encryptedName);
        String[] decodedString = new String(decryptCipher.doFinal(decoded)).split(",");
        return new OrignalFileName(Long.parseLong(decodedString[0]),decodedString[1]);
    }
    public TemporaryNameImpl(@Value("${video.fileGeneration.secret}") String KEY) {
        this.key = KEY.getBytes(StandardCharsets.US_ASCII);

    }

    @Override
    public String generateTempName(@Valid GenerateTemporaryNameCommand command) {
        String data = command.userId()+","+command.orgFileName()+","+Instant.now();
        try{
            return encrypt(data);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrignalFileName getOrignalFileName(String temporaryFileName) {
        try{
            return decrypt(temporaryFileName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}