package com.master.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    private final RepositoryAwareUserDetailsService myUserDetailService;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebConfig(RepositoryAwareUserDetailsService myUserDetailService,
                             JwtRequestFilter jwtRequestFilter) {
        this.myUserDetailService = myUserDetailService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user/login")
                .permitAll()


                .antMatchers(HttpMethod.POST, "/api/role")
                .hasAuthority("Dodavanje nove uloge")
                .antMatchers(HttpMethod.POST, "/api/user")
                .hasAuthority("Dodavanje novog uposlenika")
                .antMatchers(HttpMethod.POST, "/api/contract")
                .hasAuthority("Dodavanje novog ugovora")
                .antMatchers(HttpMethod.DELETE, "/api/contract/{id}")
                .hasAuthority("Brisanje ugovora")
                .antMatchers(HttpMethod.GET, "/api/contract")
                .hasAuthority("Pregled svih ugovora")
                .antMatchers(HttpMethod.GET, "/api/contract/{id}")
                .hasAuthority("Pregled ugovora po rednom broju")
                .antMatchers(HttpMethod.POST, "/api/criteria")
                .hasAnyAuthority("Dodavanje novog kriterija u sistem")
                .antMatchers(HttpMethod.GET, "/api/criteria")
                .hasAnyAuthority("Pregled svih kriterija")
                .antMatchers(HttpMethod.GET, "/api/criteria/{id}")
                .hasAnyAuthority("Pregled kriterija po rednom broju")
                .antMatchers(HttpMethod.DELETE, "/api/criteria/{id}")
                .hasAuthority("Brisanje kriterija")

                .antMatchers(HttpMethod.GET, "/api/grade")
                .hasAnyAuthority("Pregled svih ocjena u sistemu")
                .antMatchers(HttpMethod.POST, "/api/grade")
                .hasAnyAuthority("Dodavanje nove ocjene u sistem")
                .antMatchers(HttpMethod.GET, "/api/grade/final/{suplierId}")
                .hasAuthority("Prikaz konacne ocjene za dobavljaca")
                .antMatchers(HttpMethod.GET, "/api/grade/statistic")
                .hasAuthority("Prikaz statistike")
                .antMatchers(HttpMethod.POST, "/api/permission")
                .hasAnyAuthority("Dodavanje nove permisije")
                .antMatchers(HttpMethod.DELETE, "/api/permission/{id}")
                .hasAnyAuthority("Brisanje postojece permisije")
                .antMatchers(HttpMethod.GET, "/api/permission")
                .hasAnyAuthority("Pregled svih permisija u sistemu")
                .antMatchers(HttpMethod.GET, "/api/permission/{id}")
                .hasAnyAuthority("Pregled permisije po rednom broju")
                .antMatchers(HttpMethod.DELETE, "/api/role/{id}")
                .hasAnyAuthority("Brisanje postojece uloge")
                .antMatchers(HttpMethod.GET, "/api/role")
                .hasAnyAuthority("Pregled svih uloga u sistemu")
                .antMatchers(HttpMethod.GET, "/api/role/{id}")
                .hasAnyAuthority("Pregled uloga po rednom broju")
                .antMatchers(HttpMethod.PUT, "/api/role/{id}")
                .hasAuthority("Update postojecih uloga")
                .antMatchers(HttpMethod.DELETE, "/api/supplier/{id}")
                .hasAuthority("Brisanje postojeceg dobavljaca")
                .antMatchers(HttpMethod.GET, "/api/supplier")
                .hasAnyAuthority("Pregled svih dobavljaca u sistemu")
                .antMatchers(HttpMethod.GET, "/api/supplier/{id}")
                .hasAuthority("Pregled dobavljaca po rednom broju")

                .antMatchers(HttpMethod.PUT, "/api/supplier/{id}")
                .hasAuthority("Update postojecih dobavljaca")
                .antMatchers(HttpMethod.POST, "/api/supplier")
                .hasAuthority("Dodavanje novog dobavljaca")
                .antMatchers(HttpMethod.DELETE, "/api/user/{id}")
                .hasAnyAuthority("Brisanje postojeceg uposlenika")
                .antMatchers(HttpMethod.GET, "/api/user")
                .hasAnyAuthority("Pregled svih uposlenika u sistemu")
                .antMatchers(HttpMethod.GET, "/api/user/{id}")
                .hasAnyAuthority("Pregled uposlenika po rednom broju")
                .antMatchers(HttpMethod.PUT, "/api/user/{id}")
                .hasAnyAuthority("Update postojecih uposlenika")

                .anyRequest()
                .denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}