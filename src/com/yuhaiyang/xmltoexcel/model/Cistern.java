/**
 * Copyright (C) 2016 The yuhaiyang Android Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuhaiyang.xmltoexcel.model;

import java.util.ArrayList;
import java.util.List;

public class Cistern {
    public String name;
    public List<String> values;

    public Cistern() {

    }

    public Cistern(String name) {
        this.name = name;
    }

    public Cistern(String name, String value) {
        this.name = name;
        if (values == null) {
            values = new ArrayList<>();
        }
        values.add(value);
    }

    public void addValue(String value) {
        if (values == null) {
            values = new ArrayList<>();
        }
        values.add(value);
    }

    public List<String> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }
}
