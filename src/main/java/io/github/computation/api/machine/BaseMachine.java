package io.github.computation.api.machine;

import io.github.computation.Computation;
import io.github.computation.computer.Computer;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;

public abstract class BaseMachine {
    public void start(Computer computer) {}

    public void update(Computer computer, Computer.ComputerEvent[] events) {}

    public void stop(Computer computer) {}

    @NonNullDecl
    public abstract String getType();

    public Object[] runScript(File script, Consumer<String> printCallback, Consumer<String> errorCallback) throws IOException {
        return runScript(Files.readString(script.toPath()), printCallback, errorCallback);
    }

    public abstract Object[] runScript(String script, Consumer<String> printCallback, Consumer<String> errorCallback);
}
