package org.taranix.cli.simon.variables;

public class TemperatureVariable extends CommandVariable<Float> {

    public TemperatureVariable(Float value, boolean isDefault) {
        super(value, isDefault);
    }

    public static TemperatureVariable asDefault() {
        return new TemperatureVariable(0.5f, true);
    }

    public static TemperatureVariable from(Float value) {
        return new TemperatureVariable(value, false);
    }
}
