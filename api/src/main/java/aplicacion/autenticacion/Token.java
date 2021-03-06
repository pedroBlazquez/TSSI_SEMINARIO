package aplicacion.autenticacion;

import static aplicacion.autenticacion.SecurityConstants.SECRET;
import static aplicacion.autenticacion.SecurityConstants.TOKEN_PREFIX;

import io.jsonwebtoken.Jwts;

public class Token {

    // Metodo estatico para obtener el mail que está dentro del token.
    public static String getMailFromToken(String token) {
        String mail = null;
        if (token != null) {
            mail = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        }
        
        return mail;
    }
    
}
