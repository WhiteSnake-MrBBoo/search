package com.teamn.search.Config;

import lombok.RequiredArgsConstructor;
import org.hibernate.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    //HTML에서 전달되는 변수명
    //http://chanelLang?lang=en
    private static final String LANG_PARAM_NM = "lang";

    @Bean
    public LocaleResolver localeResolver() {

        CookieLocaleResolver localeResolver = new CookieLocaleResolver(LANG_PARAM_NM);
        localeResolver.setDefaultLocale(Locale.KOREA);
        localeResolver.setCookieHttpOnly(true);
        return localeResolver;

    }

    //브라우저에서 변수(lang)이 전달될때 가로채기를 해서 언어 적용
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName(LANG_PARAM_NM);
        interceptor.setIgnoreInvalidLocale(true);

        return interceptor;

    }

    //가로채기 범위
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor())
                .excludePathPatterns("/css/**","/js/**","/images/**","/fonts/**")
                .addPathPatterns("/**"); //위에 빼고 모든 맵핑명에 적용
    }


}
