package crudcidades;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Semana 07 Segurança, aula Autenticação

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * Conjunto de autorizações de cada usuário.
         */
        auth.inMemoryAuthentication()
                .withUser("ricardo")
                .password(cifrador().encode("rs-232"))
                .authorities("listar")
                .and()
                .withUser("tuany")
                .password(cifrador().encode("1234"))
                .authorities("admin");

    }

    @Bean
    public PasswordEncoder cifrador() {
        return new BCryptPasswordEncoder();
    }

    // Semana 07 Segurança, aula Autorização
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").hasAnyAuthority("listar", "admin")
                .antMatchers("/criar").hasAnyAuthority("admin")
                .antMatchers("/excluir").hasAnyAuthority("admin")
                .antMatchers("/preparaAlterar").hasAnyAuthority("admin")
                .antMatchers("/alterar").hasAnyAuthority("admin")
                .anyRequest().denyAll()
                .and()
                .formLogin()
                // Semana 07 Segurança, aula Login/Logout
                .loginPage("/login.html").permitAll()
                .and()
                .logout().permitAll();
    }
}