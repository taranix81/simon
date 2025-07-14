package org.taranix.cli.simon.common;

import org.taranix.cafe.beans.annotations.CafeFactory;
import org.taranix.cafe.beans.annotations.CafeProvider;
import org.taranix.cli.simon.variables.TemperatureVariable;

@CafeFactory
class CommonConfiguration {

    @CafeProvider
    TemperatureVariable defaultTemperature() {
        return TemperatureVariable.asDefault();
    }
}
