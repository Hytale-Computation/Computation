package io.github.computation.component;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import io.github.computation.Computation;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.UUID;

public abstract class ComputerId<ECS_TYPE> implements Component<ECS_TYPE> {
    public UUID id = UUID.randomUUID();

    public static class Chunk extends ComputerId<ChunkStore> {
        public static final BuilderCodec<Chunk> CODEC =
                BuilderCodec.builder(Chunk.class, Chunk::new)
                        .append(
                                new KeyedCodec<>("Id", BuilderCodec.UUID_STRING),
                                (comp, id) -> comp.id = id,
                                comp -> comp.id
                        )
                        .documentation("The ID of the Computer.").add().build();

        public static ComponentType<ChunkStore, Chunk> componentType() {
            return Computation.get().computerIdChunkComponentType;
        }

        @NullableDecl
        @Override
        public Component<ChunkStore> clone() {
            Chunk comp = new Chunk();
            comp.id = this.id;
            return comp;
        }
    }

    public static class Entity extends ComputerId<EntityStore> {
        public static final BuilderCodec<Entity> CODEC =
                BuilderCodec.builder(Entity.class, Entity::new)
                        .append(
                                new KeyedCodec<>("Id", BuilderCodec.UUID_STRING),
                                (comp, id) -> comp.id = id,
                                comp -> comp.id
                        )
                        .documentation("The ID of the Computer.").add().build();

        public static ComponentType<EntityStore, Entity> componentType() {
            return Computation.get().computerIdEntityComponentType;
        }

        @NullableDecl
        @Override
        public Component<EntityStore> clone() {
            Entity comp = new Entity();
            comp.id = this.id;
            return comp;
        }
    }
}
