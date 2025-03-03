package in.nursery.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import in.nursery.model.Customer;
import in.nursery.model.Staff;
import in.nursery.repo.CustomerRepo;
import in.nursery.repo.StaffRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


//  JWT Service class that provides various methods for token operations.

@Component
public class JwtService {

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private StaffRepo repo2;

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

   
     // Extracts the userName from the JWT token.
     
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    
     // Extracts the expiration date from the JWT token.
     
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    
     // Extracts a specific claim from the JWT token.
     
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    
    // Extracts all claims from the JWT token.
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    
    // Checks if the JWT token is expired.
  
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

   
    // Validates the JWT token against the UserDetails.
     
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    
     // Generates a JWT token based on the userName.
     
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
        
    }

   
    // Creates a JWT token with the given claims and userName.
    
      private String createToken(Map<String, Object> claims, String username) {
        Customer customer = repo.findByEmail(username);
        if (customer != null) {
            return Jwts.builder().setSubject(username).claim("customerId", customer.getcId()).claim("userType", "Customer")
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 1))
                    .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        }
        Staff staff = repo2.findByEmail(username);
        if (staff != null) {
            return Jwts.builder().setSubject(username).claim("staffId", staff.getStaffId()).claim("userType", "Staff")
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 1))
                    .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        }
        return null;
    }

   
     // Retrieves the Customer ID from the JWT token.
     
    @SuppressWarnings("deprecation")
    public Integer getCustomerIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody();
        return claims.get("customerId", Integer.class);
    }

    
     // Retrieves the Staff ID from the JWT token.
     
    public Integer getStaffIdFromToken(String token) {
        @SuppressWarnings("deprecation")
        Claims claims = Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody();
        return claims.get("staffId", Integer.class);
    }

    
      // Retrieves the signing key for JWT token verification.
    
    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
