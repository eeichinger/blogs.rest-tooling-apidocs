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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

/**
 * @author Erich Eichinger
 * @since 05/05/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder(builderMethodName = "quoteBuilder")
@JsonRootName("quoteResponse")
public class QuoteResponse {
    String type;
    Value value;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @Builder(builderMethodName = "valueBuilder")
    public static class Value {
        Long id;
        String quote;
    }
}
