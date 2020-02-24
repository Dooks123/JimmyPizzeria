package com.jimmy.pizzeria.util;

import com.jimmy.pizzeria.helpers.TextHelper;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {
    private static final String TAG = Utils.getClassTag(CryptoUtils.class);

    private static final int pad = 3;
    private static final String kiv = "P1z5@0rd";
    private static final Charset charset = Charset.forName("UTF-8");
    private static final String algorithm = "DES";
    private static final String transformation = algorithm + "/CBC/PKCS7Padding";

    private static void addProvider() {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception ex) {
            Logger.LogError(TAG + ".addProvider", ex.getMessage(), ex);
        }
    }

    public static boolean encrypt(String key, String json, File outputFile) {
        try {
            addProvider();

            SecretKeySpec sks = new SecretKeySpec(key.getBytes(charset), algorithm);
            IvParameterSpec iv = new IvParameterSpec(kiv.getBytes(charset));
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, sks, iv);

            StringBuilder EncKey = new StringBuilder();
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                try (CipherOutputStream cos = new CipherOutputStream(out, cipher)) {
                    cos.write((json).getBytes());
                    cos.flush();
                }

                for (byte b : out.toByteArray()) {
                    EncKey.append(TextHelper.padLeft(String.valueOf(((int) b) & 0xff), pad, "0"));
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                outputStream.write(EncKey.toString().getBytes());
            }

            return true;
        } catch (Exception ex) {
            Logger.LogError(TAG + ".encrypt", ex.getMessage(), ex);
            return false;
        }
    }
}