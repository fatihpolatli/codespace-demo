package com.mvc.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(final ViewControllerRegistry registry) {

		// super.addViewControllers(registry);
		registry.addViewController("/").setViewName("forward:/login");
		registry.addViewController("/login");
		registry.addViewController("/loginRememberMe");
		registry.addViewController("/customLogin");
		registry.addViewController("/registration.html");
		registry.addViewController("/registrationCaptcha.html");
		registry.addViewController("/logout.html");
		registry.addViewController("/homepage.html");
		registry.addViewController("/expiredAccount.html");
		registry.addViewController("/badUser.html");
		registry.addViewController("/emailError.html");
		registry.addViewController("/home.html");
		registry.addViewController("/invalidSession.html");
		registry.addViewController("/console.html");
		registry.addViewController("/admin.html");
		registry.addViewController("/successRegister.html");
		registry.addViewController("/forgetPassword.html");
		registry.addViewController("/updatePassword.html");
		registry.addViewController("/changePassword.html");
		registry.addViewController("/users.html");
		registry.addViewController("/qrcode.html");

	}

	@Override
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/");
	}

	/*
	 * @Bean public EmailValidator usernameValidator() { return new
	 * EmailValidator(); }
	 * 
	 * @Bean public PasswordMatchesValidator passwordMatchesValidator() { return new
	 * PasswordMatchesValidator(); }
	 * 
	 * @Bean
	 * 
	 * @ConditionalOnMissingBean(RequestContextListener.class) public
	 * RequestContextListener requestContextListener() { return new
	 * RequestContextListener(); }
	 * 
	 * @Override public Validator getValidator() { LocalValidatorFactoryBean
	 * validator = new LocalValidatorFactoryBean();
	 * validator.setValidationMessageSource(messageSource); return validator; }
	 */

}
