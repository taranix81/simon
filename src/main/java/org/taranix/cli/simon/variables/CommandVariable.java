package org.taranix.cli.simon.variables;

public abstract class CommandVariable<T> {

    private final T value;
    private final boolean isDefault;

    public CommandVariable(T value, boolean isDefault) {
        this.value = value;
        this.isDefault = isDefault;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public T get() {
        return value;
    }
}