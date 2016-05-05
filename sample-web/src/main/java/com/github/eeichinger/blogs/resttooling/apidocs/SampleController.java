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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.github.eeichinger.blogs.resttooling.apidocs.QuoteResponse.Value.*;
import static com.github.eeichinger.blogs.resttooling.apidocs.QuoteResponse.*;

/**
 * @author Erich Eichinger
 * @since 05/05/2016
 */
@RestController
public class SampleController {

    @RequestMapping(path = "/quote/sync", method= RequestMethod.GET)
    public QuoteResponse fetchQuoteSynchronously() {
        return quoteBuilder()
            .type("success")
            .value(valueBuilder()
                    .id(8l)
                    .quote("my really smart quote").build()
            )
            .build();
    }
}
