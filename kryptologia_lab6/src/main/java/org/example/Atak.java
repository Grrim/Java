package org.example;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.ECPoint;

import iaik.security.ec.common.ECParameterSpec;
import iaik.security.ec.common.ECStandardizedParameterFactory;
import iaik.security.ec.common.SecurityStrength;

public class Atak
{

    public static void main(String[] args)
    {
        System.out.println("---- Demo implementacji ECDSA: Tomasz Hyla 2022");

        //---Parametry systemowe
        //-krzywa eliptyczna ustandaryzowana
        ECParameterSpec ec_params = ECStandardizedParameterFactory.getParametersByName("secp521r1");

        iaik.security.ec.common.EllipticCurve ec = ec_params.getCurve(); //tej używamy dalej
        iaik.security.ec.math.curve.EllipticCurve ec2 = ec.getIAIKCurve(); //klasa EC w innej bibliotece...

        System.out.println(ec2.toString());
        BigInteger n = ec2.getOrder();
        int size = ec2.getField().getFieldSize();

        System.out.println("Rozmiar w bitach: " + size + " liczba elementów (n)= " + n);
        //-generator liczb losowych
        final SecureRandom random = SecurityStrength.getSecureRandom(SecurityStrength.getSecurityStrength(size));

        //---Generowanie kluczy
        //prywatny
        BigInteger dA = new BigInteger(size - 1, random);
        //publiczny
        ECPoint QA = ec.multiplyGenerator(dA); // Q_A=d_A*G

        //---Podpisywanie
        //1-2.
        BigInteger z1 = BigInteger.ZERO;
        BigInteger z2 = BigInteger.ZERO;

        try
        {
            String m1 = "Kryptologia 2022";
            String m2 = "Kryptologia ZUT";
            MessageDigest sha = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest1 = sha.digest(m1.getBytes());
            z1 = new BigInteger(1, messageDigest1);
            byte[] messageDigest2 = sha.digest(m2.getBytes());
            z2 = new BigInteger(1, messageDigest2);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        //3.
        BigInteger k = new BigInteger(size - 1, random);
        var kG = ec.multiplyGenerator(k);

        BigInteger r1 = BigInteger.ZERO;
        BigInteger s1 = BigInteger.ZERO;
        do
        {
            //5.
            r1 = kG.getAffineX().mod(n);
            System.out.println(r1.toString());
            s1 = k.modInverse(n).multiply(z1.add(r1.multiply(dA))).mod(n);
        }
        while (r1.equals(BigInteger.ZERO) || s1.equals(BigInteger.ZERO));

        BigInteger r2 = BigInteger.ZERO;
        BigInteger s2 = BigInteger.ZERO;
        do
        {
            //5.
            r2 = kG.getAffineX().mod(n);
            System.out.println(r2.toString());
            s2 = k.modInverse(n).multiply(z2.add(r2.multiply(dA))).mod(n);
        }
        while (r2.equals(BigInteger.ZERO) || s2.equals(BigInteger.ZERO));

        var z3 = z1.subtract(z2);
        if (z3.signum() == 1) {
            z3 = z3.mod(n);
        } else {
            z3 = z3.add(n);
        }

        var s3 = s1.subtract(s2);
        if (s3.signum() == 1) {
            s3 = s3.mod(n);
        } else {
            s3 = s3.add(n);
        }

        BigInteger sss = s3.modInverse(n);

        BigInteger kA = z3.multiply(sss).mod(n);
        BigInteger dAA = s1.multiply(kA).subtract(z1).multiply(r1.modInverse(n)).mod(n);

        System.out.println("Znaleziono klucz: " + dA.equals(dAA));
    }
}