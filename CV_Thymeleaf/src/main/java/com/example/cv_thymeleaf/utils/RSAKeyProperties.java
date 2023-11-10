package com.example.cv_thymeleaf.utils;

import lombok.Getter;
import lombok.Setter;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@Setter
public class RSAKeyProperties {

  private RSAPublicKey publicKey;
  private RSAPrivateKey privateKey;

  public RSAKeyProperties() {
    KeyPair pair = KeyGeneratorUtility.generateRsaKey();
    this.publicKey = (RSAPublicKey) pair.getPublic();
    this.privateKey = (RSAPrivateKey) pair.getPrivate();
  }

}
