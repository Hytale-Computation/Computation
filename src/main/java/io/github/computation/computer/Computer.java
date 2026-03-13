package io.github.computation.computer;

import io.github.computation.api.machine.BaseMachine;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Computer {
    private final UUID id;
    private BaseMachine machine;

    private final ConcurrentLinkedQueue<ComputerEvent> events = new ConcurrentLinkedQueue<>();

    public Computer(UUID id, BaseMachine machine) {
        this.id = id;
        this.machine = machine;
    }

    public UUID getId() {
        return this.id;
    }

    public BaseMachine getMachine() {
        return this.machine;
    }

    public void setMachine(BaseMachine machine) {
        this.machine = machine;
    }

    public void queueEvent(String name, Object... values) {
        this.events.add(new ComputerEvent(name, values));
    }

    public record ComputerEvent(String name, Object... values) { }
}
