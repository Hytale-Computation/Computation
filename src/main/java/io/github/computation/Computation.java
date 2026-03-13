package io.github.computation;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import io.github.computation.api.machine.factory.MachineFactory;
import io.github.computation.commands.ExampleCommand;
import io.github.computation.component.base.ComputerComponent;
import io.github.computation.computer.Computer;
import io.github.computation.events.ExampleEvent;
import io.github.computation.api.machine.BaseMachine;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Computation extends JavaPlugin {
    private static Computation INSTANCE;
    public static HashMap<String, MachineFactory<?>> MACHINES = new HashMap<>();
    public static HashMap<UUID, Computer> COMPUTERS = new HashMap<>();

    public Computation(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
    }

    public static Computation get() {
        return INSTANCE;
    }

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

    public static void addComputer(UUID id, @NonNullDecl Computer computer) {
        COMPUTERS.put(id, computer);
    }

    @NullableDecl
    public static Computer getComputer(UUID id) {
        return COMPUTERS.get(id);
    }

    public static void removeComputer(UUID id) {
        COMPUTERS.remove(id);
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new ExampleCommand("example", "An example command"));
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
    }
}