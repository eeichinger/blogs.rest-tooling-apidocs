/*
 Copyright 2016 the original author(s)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.github.eeichinger.blogs.resttooling.apidocs;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.*;

/**
 * @author Erich Eichinger
 * @since 05/05/2016
 */
@Configuration
@EnableSwagger2
@PropertySource("classpath:/springfox-configuration.properties")
public class Swagger2Configuration {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            // prefix uris with '/sampleweb' context path
            .pathMapping("/sampleweb")
            .consumes(Sets.newHashSet(MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE))
            .produces(Sets.newHashSet(MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE))
            // prevent swagger-spec 'basepath' property from being set. This confuses swagger-ui.
            .pathProvider(new AbstractPathProvider() {
                @Override
                protected String applicationPath() {
                    return null;
                }

                @Override
                protected String getDocumentationPath() {
                    return "";
                }
            })
            .select()
            // document all @RestController annotated @RequestMappings
            .apis(withClassAnnotation(RestController.class))
            .build()
            ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("My SampleWeb API documentation"
            , "documentation for module \"Sample Web\"."
            , ""
            , ""
            , new Contact("salzamt@example.com", "", "")
            , ""
            , ""
        );
    }
}
