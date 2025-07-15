package org.taranix.cli.simon.variables;

public class GeminiModelVariable extends CommandVariable<String> {

    private GeminiModelVariable(String value, boolean isDefault) {
        super(value, isDefault);
    }

    public static GeminiModelVariable asDefault(String value) {
        return new GeminiModelVariable(value, true);
    }

    public static GeminiModelVariable asCustom(String value) {
        return new GeminiModelVariable(value, false);
    }
}
