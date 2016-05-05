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

package doc;

import java.io.File;

import doc.utils.AbstractSwaggerSpecGenerator;
import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import org.junit.Test;

/**
 * @author Erich Eichinger
 * @since 05/05/2016
 */
public class SwaggerSpec2AsciiDocConverter {

    @Test
    public void convert() throws Exception {
        final File swaggerJsonPath = new File(AbstractSwaggerSpecGenerator.SWAGGER_SPEC_OUTPUT_FILENAME);

        String loc = swaggerJsonPath.toString();
        Swagger2MarkupConverter
            .from(loc)
            .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
            .withExamples("src/docs/generated")
            .withDescriptions("src/docs")
            .build()
            .intoFolder("src/docs/generated");
    }
}
