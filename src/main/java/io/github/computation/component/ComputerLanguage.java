package io.github.computation.component;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.function.FunctionCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import io.github.computation.Computation;
import io.github.computation.api.ComputationAPI;
import io.github.computation.api.machine.factory.MachineFactory;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class ComputerLanguage<ECS_TYPE> implements Component<ECS_TYPE> {
    public MachineFactory<ECS_TYPE> machine;

    private static final FunctionCodec<String, MachineFactory> MACHINE_CODEC =
            new FunctionCodec<>(BuilderCodec.STRING, ComputationAPI::getMachineFactory, ComputationAPI::getLanguage);

    public static class Chunk extends ComputerLanguage<ChunkStore> {
        public static final BuilderCodec<Chunk> CODEC =
                BuilderCodec.builder(Chunk.class, Chunk::new)
                        .append(
                                new KeyedCodec<>("Machine", MACHINE_CODEC),
                                (comp, machine) -> comp.machine = machine,
                                comp -> comp.machine
                        )
                        .documentation("The Language Machine of the Computer.").add().build();

        public static ComponentType<ChunkStore, Chunk> componentType() {
            return Computation.get().computerLanguageChunkComponentType;
        }

        @NullableDecl
        @Override
        public Component<ChunkStore> clone() {
            Chunk comp = new Chunk();
            comp.machine = this.machine;
            return comp;
        }
    }

    public static class Entity extends ComputerLanguage<EntityStore> {
        public static final BuilderCodec<Entity> CODEC =
                BuilderCodec.builder(Entity.class, Entity::new)
                        .append(
                                new KeyedCodec<>("Machine", MACHINE_CODEC),
                                (comp, machine) -> comp.machine = machine,
                                comp -> comp.machine
                        )
                        .documentation("The Language Machine of the Computer.").add().build();

        public static ComponentType<EntityStore, Entity> componentType() {
            return Computation.get().computerLanguageEntityComponentType;
        }

        @NullableDecl
        @Override
        public Component<EntityStore> clone() {
            Entity comp = new Entity();
            comp.machine = this.machine;
            return comp;
        }
    }
}
