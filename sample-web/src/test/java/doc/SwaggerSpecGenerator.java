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

import com.github.eeichinger.blogs.resttooling.apidocs.ApplicationConfiguration;
import doc.utils.AbstractSwaggerSpecGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Erich Eichinger
 * @since 05/05/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
    classes = {ApplicationConfiguration.class}
)
public class SwaggerSpecGenerator extends AbstractSwaggerSpecGenerator {

    @Override
    protected DefaultMockMvcBuilder createMockMvcBuilder() {
        return MockMvcBuilders.webAppContextSetup(super.applicationContext);
    }

    @Test
    public void generateSpec() throws Exception {
        // just to prevent JUnit from complaining - actual work is done in @After of AbstractSwaggerExport
    }
}
