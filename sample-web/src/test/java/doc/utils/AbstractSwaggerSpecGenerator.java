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

package doc.utils;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.Validate;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author Erich Eichinger
 * @since 05/05/2016
 */
public abstract class AbstractSwaggerSpecGenerator {

    public static final String SWAGGER_SPEC_OUTPUT_FILENAME = "./src/main/webapp/system/api-docs.json";

    @Autowired
    protected WebApplicationContext applicationContext;

    protected MockMvc mockMvc;

    @Before
    public final void createMockMvc() throws Exception {
        this.mockMvc = createMockMvcBuilder()
//            .apply(documentationConfiguration(restDocumentationRule))
            .build();
    }

    protected abstract DefaultMockMvcBuilder createMockMvcBuilder();

    @Before
    public void export_swagger_spec() throws Exception {
        String swaggerControllerPath = applicationContext.getEnvironment().getProperty("springfox.documentation.swagger.v2.path");
        Validate.notEmpty(swaggerControllerPath, "property springfox.documentation.swagger.v2.path must be set");

        final MvcResult mvcResult = mockMvc
            .perform(get(swaggerControllerPath))
            .andReturn();

        byte[] data = mvcResult.getResponse().getContentAsByteArray();

        assertThat(mvcResult.getResponse().getStatus(), equalTo(200));
        assertThat(data.length, Matchers.greaterThan(0));

        String strData = new String(prettyPrintJson(data), "utf-8");

        final File swaggerJsonPath = new File(SWAGGER_SPEC_OUTPUT_FILENAME);
        FileUtils.deleteQuietly(swaggerJsonPath.getParentFile());
        mkdir(swaggerJsonPath);

        FileUtils.writeStringToFile(swaggerJsonPath, strData, false);
    }

    @SneakyThrows
    private static byte[] prettyPrintJson(byte[] text) {
        final ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(text, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(obj);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    protected void mkdir(File swaggerJsonPath) {
        final boolean ok = swaggerJsonPath.getParentFile().mkdir();
        if (!ok) return;
    }
}
