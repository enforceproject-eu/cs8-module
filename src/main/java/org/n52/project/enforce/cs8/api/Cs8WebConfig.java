package org.n52.project.enforce.cs8.api;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * WebConfig class.
 * </p>
 *
 * @author Benjamin Pross (b.pross@52north.org)
 * @since 1.0.0
 */
@Configuration
public class Cs8WebConfig implements WebMvcConfigurer {
    
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new Cs8MessageConverter());
    }
    
    @Bean
    GroupedOpenApi c8Apis() {
      return GroupedOpenApi.builder().group("cs8").pathsToMatch("/cs8_pfos_data/**").build();
    }
}
