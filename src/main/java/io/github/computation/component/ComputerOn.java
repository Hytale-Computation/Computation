package io.github.computation.component;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import io.github.computation.Computation;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class ComputerOn<ECS_TYPE> implements Component<ECS_TYPE> {
    public static class Chunk extends ComputerOn<ChunkStore> {
        public static final BuilderCodec<Chunk> CODEC =
                BuilderCodec.builder(Chunk.class, Chunk::new).build();

        public static ComponentType<ChunkStore, Chunk> componentType() {
            return Computation.get().computerOnChunkComponentType;
        }

        @NullableDecl
        @Override
        public Component<ChunkStore> clone() {
            return new Chunk();
        }
    }

    public static class Entity extends ComputerOn<EntityStore> {
        public static final BuilderCodec<Entity> CODEC =
                BuilderCodec.builder(Entity.class, Entity::new).build();

        public static ComponentType<EntityStore, Entity> componentType() {
            return Computation.get().computerOnEntityComponentType;
        }

        @NullableDecl
        @Override
        public Component<EntityStore> clone() {
            return new Entity();
        }
    }
}
