package io.github.computation.api;

import io.github.computation.api.machine.factory.MachineFactory;
import io.github.computation.computer.Computer;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ComputationAPI {
    private static final HashMap<String, MachineFactory<?>> MACHINES = new HashMap<>();
    private static final HashMap<UUID, Computer> COMPUTERS = new HashMap<>();

    public static void registerMachine(@NonNullDecl String language, @NonNullDecl MachineFactory<?> machineFactory) {
        if (MACHINES.containsKey(language))
            throw new IllegalArgumentException(language + " is already registered!");
        else
            MACHINES.put(language, machineFactory);
    }

    @NonNullDecl
    public static MachineFactory<?> getMachineFactory(String language) {
        MachineFactory<?> machine = MACHINES.get(language);
        if (machine == null)
            throw new IllegalArgumentException(language + " is not registered!");
        return machine;
    }

    @NonNullDecl
    public static String getLanguage(MachineFactory<?> factory) {
        for (Map.Entry<String, MachineFactory<?>> entry : MACHINES.entrySet()) {
            if (entry.getValue() == factory) return entry.getKey();
        }
        throw new IllegalArgumentException("This factory is not registered!");
    }

    public static void addComputer(@NonNullDecl Computer computer) {
        COMPUTERS.put(computer.getId(), computer);
    }

    @NullableDecl
    public static Computer getComputer(UUID id) {
        return COMPUTERS.get(id);
    }

    public static void removeComputer(UUID id) {
        COMPUTERS.remove(id);
    }
}
