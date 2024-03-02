package com.sudoleg.portfoliomanager.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JSON Web Tokens (JWT) operations such as token generation,
 * validation, and claims extraction.
 *
 * <p>This service provides functionality to:</p>
 * <ul>
 *   <li>Generate a JWT for a given user with optional additional claims.</li>
 *   <li>Extract specific claims like username or expiration date from a JWT.</li>
 *   <li>Validate a JWT's integrity and ensure it is not expired.</li>
 * </ul>
 */
@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private static String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    /**
     * Validates the JWT token's integrity and relevance for the given user details.
     *
     * @param jwt         The JWT token to validate.
     * @param userDetails The user details against which the token is to be validated.
     * @return true if the token is valid (i.e., it belongs to the user and is not expired); false otherwise.
     */
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    /**
     * Checks if the provided JWT token has expired.
     *
     * @param jwt The JWT token to check for expiration.
     * @return true if the token has expired; false otherwise.
     */
    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param jwt The JWT token from which the expiration date is to be extracted.
     * @return The expiration Date extracted from the token.
     */
    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    /**
     * Extracts the username (subject claim) from a JWT.
     *
     * @param jwt the JWT from which the username is to be extracted.
     * @return The username (subject) extracted from the JWT.
     */
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    /**
     * Retrieves a specific claim from a JWT using a provided function.
     *
     * @param jwt            the JWT from which to extract the claim, must be a valid JWT string.
     * @param claimsResolver a function defining how to extract a claim of type T from the Claims object.
     * @param <T>            the data type of the claim being extracted.
     * @return the extracted claim of type T, determined by the claimsResolver function.
     */
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(jwt)
                .getBody();
    }

    /**
     * Generates a signing key for JWT verification based on the provided secret key.
     *
     * @return A signing key derived from the base64-decoded secret key using HMAC-SHA algorithm.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates a JWT token for the given user without additional claims.
     *
     * @param userDetails The user details for which the token is to be generated. This includes the username.
     * @return A JWT token as a String, signed and encoded, containing the user's username,
     * issued date, and expiration time, without any extra claims.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token for the given user with additional claims.
     *
     * @param extraClaims Additional information or attributes about the user that should be included in the JWT.
     * @param userDetails The user details for which the token is to be generated, typically includes the username.
     * @return A JWT token as a String, signed and encoded, containing the provided claims, user's username,
     * issued date, and expiration time.
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
