package com.levi.smarttracker.security

import javax.sql.DataSource

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(private val unauthorizedHandler: JWTAuthenticationEntryPoint,
                     private val jwtTokenUtil: JWTTokenUtil,
                     private val jwtUserDetailsService: JWTUserDetailsService) : WebSecurityConfigurerAdapter() {

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun dataSourceInitializer(dataSource: DataSource): DataSourceInitializer {
        val resourceDatabaseCreator = ResourceDatabasePopulator()
        resourceDatabaseCreator.addScript(ClassPathResource("/data.sql"))

        val dataSourceInitializer = DataSourceInitializer()
        dataSourceInitializer.setDataSource(dataSource)
        dataSourceInitializer.setDatabasePopulator(resourceDatabaseCreator)
        return dataSourceInitializer
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationTokenFilterBean(): JWTAuthenticationTokenFilter
            = JWTAuthenticationTokenFilter(jwtTokenUtil, jwtUserDetailsService)

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/login/**", "/configuration/security", "/webjars/**")
                .permitAll().anyRequest().authenticated()
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)
        httpSecurity.headers().cacheControl()
    }

}
