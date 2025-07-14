package org.taranix.cli.simon.variables;

public class TokenOutputVariable extends CommandVariable<Integer> {
    public TokenOutputVariable(Integer value, boolean isDefault) {
        super(value, isDefault);
    }

    public static TokenOutputVariable from(Integer value) {
        return new TokenOutputVariable(value, false);
    }
}
