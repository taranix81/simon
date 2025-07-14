package org.taranix.cli.simon.common;

import org.taranix.cafe.beans.annotations.CafeService;
import org.taranix.cafe.beans.converters.CafeConverter;

@CafeService
public class StringToFloatConverter implements CafeConverter<String, Float> {
    @Override
    public Float convert(String s) {
        return Float.parseFloat(s);
    }
}
