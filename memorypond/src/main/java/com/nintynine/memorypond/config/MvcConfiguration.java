package com.nintynine.memorypond.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//@Configuration
//@EnableWebMvc
//public class MvcConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("classpath:/WEB-INF/js/");
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("classpath:/WEB-INF/js/");
//        registry.addResourceHandler("/WEB-INF/css/**")
//                .addResourceLocations("classpath:/WEB-INF/css/");
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("classpath:/WEB-INF/img/");
//    }
//
//    @Bean
//    public ViewResolver getViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/view/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//}
