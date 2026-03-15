package io.github.computation.computer;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import io.github.computation.api.machine.BaseMachine;
import io.github.computation.api.machine.factory.MachineFactory;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Computer {
    private final UUID id;
    private MachineFactory<?> machineFactory;
    private BaseMachine machine;

    private final ConcurrentLinkedQueue<ComputerEvent> events = new ConcurrentLinkedQueue<>();

    public Computer(UUID id, MachineFactory<?> machineFactory) {
        this.id = id;
        this.machineFactory = machineFactory;
    }

    public UUID getId() {
        return this.id;
    }

    public <ECS_TYPE> void initializeMachine(int index, @NonNullDecl ArchetypeChunk<ECS_TYPE> archetypeChunk, @NonNullDecl Store<ECS_TYPE> store) {
        this.machine = ((MachineFactory<ECS_TYPE>) this.machineFactory).createMachine(index, archetypeChunk, store);
    }

    @NullableDecl
    public BaseMachine getMachine() {
        return this.machine;
    }

    public void queueEvent(String name, Object... values) {
        this.events.add(new ComputerEvent(name, values));
    }

    public void tick(float dt) {
        if (this.machine != null) {
            this.machine.update(dt, this, this.events.toArray(new ComputerEvent[0]));
            this.events.clear();
        }
    }

    public record ComputerEvent(String name, Object... values) { }
}
