package org.taranix.cli.simon.variables;

public class ModelAiVariable extends CommandVariable<String> {

    private ModelAiVariable(String value, boolean isDefault) {
        super(value, isDefault);
    }

    public static ModelAiVariable asDefault(String value) {
        return new ModelAiVariable(value, true);
    }

    public static ModelAiVariable asCustom(String value) {
        return new ModelAiVariable(value, false);
    }
}
