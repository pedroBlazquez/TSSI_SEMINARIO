package aplicacion.autenticacion;

import com.fasterxml.jackson.databind.ObjectMapper;

import conexion.Conexion;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import modelos.Usuario;
import negocio.UsuarioNegocio;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static aplicacion.autenticacion.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private Usuario creds;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
        HttpServletResponse res) throws AuthenticationException {

        try {
            creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Usuario.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getMail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain,
        Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        JSONObject json = new JSONObject();
        try {
            Conexion cn = new Conexion();
            cn.abrirConexion();
            Usuario usuario = UsuarioNegocio.getUsuarioByMail(cn,creds.getMail());
            cn.cerrarConexion();
            json.put("usuario", usuario.toJson());
            json.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        res.getWriter().write(json.toString());
        res.getWriter().flush();
        res.getWriter().close();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {

        response.sendError(HttpStatus.BAD_REQUEST.value(), BAD_CREDENTIALS_MSG);
    }
}
