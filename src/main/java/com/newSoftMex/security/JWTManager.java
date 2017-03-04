package com.newSoftMex.security;

import com.newSoftMex.config.JpaConfiguration;
import com.newSoftMex.model.Credentials;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by alan.flores on 1/10/17.
 */
public class JWTManager {

    private SecretKey secretKey = null;

    private String encodedKey = "ggIpqzi6HYWyUsWpOnV0uA==";

    public String createJWT(String username, String password, String role,Long id, long ttlMillis, String image, Date premiumDate) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encodedKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        /*JwtBuilder builder = Jwts.builder().setHeaderParam("typ","JWT")
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);*/

        Claims claims = new DefaultClaims();
        claims.put("id",id);
        claims.put("username",username);
        claims.put("password",password);
        claims.put("role",role);
        claims.put("image",image);
        claims.put("premiumDate",premiumDate);
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ","JWT")
                .setClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Credentials parseJWT(String jwt) throws Exception {

        System.out.println(jwt);
        Credentials credentials = new Credentials();
        //This line will throw an exception if it is not a signed JWS (as expected)

        Jws<Claims> claims2 = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(encodedKey))
                .parseClaimsJws(jwt);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(encodedKey))
                .parseClaimsJws(jwt).getBody();

        credentials.setId(new Long(claims.get("id",Integer.class)));
        credentials.setUsername(claims.get("username",String.class));
        credentials.setPassword(claims.get("password",String.class));
        credentials.setRole(claims.get("role",String.class));
        credentials.setImage(claims.get("image",String.class));
        credentials.setToken(jwt);
        credentials.setPremiumDate(claims.get("premiumDate",Date.class));
        return credentials;
    }

    public void createSecret(){
        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // get base64 encoded version of the key
        //encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        encodedKey = DatatypeConverter.printBase64Binary(secretKey.getEncoded());
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String getEncodedKey() {
        System.out.println(encodedKey);
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }
}
