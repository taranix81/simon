package org.taranix.cli.simon.common;

import org.taranix.cafe.beans.annotations.CafeFactory;
import org.taranix.cafe.beans.annotations.CafeProperty;
import org.taranix.cafe.beans.annotations.CafeProvider;
import org.taranix.cli.simon.variables.TemperatureVariable;
import org.taranix.cli.simon.variables.TokenOutputVariable;

@CafeFactory
class CommonConfiguration {

    @CafeProperty(name = "ai.output.token")
    private Integer maxTokenOutput;

    @CafeProvider
    TemperatureVariable defaultTemperature() {
        return TemperatureVariable.asDefault();
    }

    @CafeProvider
    TokenOutputVariable defaultMaxTokenOutput() {
        return new TokenOutputVariable(maxTokenOutput, true);
    }
}
