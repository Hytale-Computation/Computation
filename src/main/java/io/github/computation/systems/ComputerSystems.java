package io.github.computation.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.HolderSystem;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import io.github.computation.api.ComputationAPI;
import io.github.computation.component.ComputerId;
import io.github.computation.component.ComputerLanguage;
import io.github.computation.component.ComputerOn;
import io.github.computation.computer.Computer;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class ComputerSystems {
    public static class ChunkSystems {
        public static class ComputerHolderSystem extends HolderSystem<ChunkStore> {
            private final ComponentType<ChunkStore, ComputerId.Chunk> computerIdComponentType;
            private final ComponentType<ChunkStore, ComputerLanguage.Chunk> computerLanguageComponentType;

            public ComputerHolderSystem(
                    ComponentType<ChunkStore, ComputerId.Chunk> computerIdComponentType,
                    ComponentType<ChunkStore, ComputerLanguage.Chunk> computerLanguageComponentType
            ) {
                this.computerIdComponentType = computerIdComponentType;
                this.computerLanguageComponentType = computerLanguageComponentType;
            }

            @Override
            public void onEntityAdd(@NonNullDecl Holder<ChunkStore> holder, @NonNullDecl AddReason addReason, @NonNullDecl Store<ChunkStore> store) {
                ComputerId.Chunk id = holder.getComponent(this.computerIdComponentType);
                ComputerLanguage.Chunk language = holder.getComponent(this.computerLanguageComponentType);
                assert id != null;
                assert language != null;
                Computer computer = new Computer(id.id, language.machine);
                ComputationAPI.addComputer(computer);
            }

            @Override
            public void onEntityRemoved(@NonNullDecl Holder<ChunkStore> holder, @NonNullDecl RemoveReason removeReason, @NonNullDecl Store<ChunkStore> store) {
                ComputerId.Chunk id = holder.getComponent(this.computerIdComponentType);
                assert id != null;
                Computer computer = ComputationAPI.getComputer(id.id);
                assert computer != null;
                assert computer.getMachine() != null;
                computer.getMachine().stop(computer);
                ComputationAPI.removeComputer(id.id);
            }

            @NullableDecl
            @Override
            public Query<ChunkStore> getQuery() {
                return Query.and(this.computerIdComponentType, this.computerLanguageComponentType);
            }
        }

        public static class ComputerTickingSystem extends EntityTickingSystem<ChunkStore> {
            private final ComponentType<ChunkStore, ComputerOn.Chunk> computerOnComponentType;
            private final ComponentType<ChunkStore, ComputerId.Chunk> computerIdComponentType;

            public ComputerTickingSystem(
                    ComponentType<ChunkStore, ComputerOn.Chunk> computerOnComponentType,
                    ComponentType<ChunkStore, ComputerId.Chunk> computerIdComponentType
            ) {
                this.computerOnComponentType = computerOnComponentType;
                this.computerIdComponentType = computerIdComponentType;
            }

            @Override
            public void tick(float dt, int index, @NonNullDecl ArchetypeChunk<ChunkStore> archetypeChunk, @NonNullDecl Store<ChunkStore> store, @NonNullDecl CommandBuffer<ChunkStore> commandBuffer) {
                ComputerId.Chunk id = archetypeChunk.getComponent(index, this.computerIdComponentType);
                assert id != null;
                Computer computer = ComputationAPI.getComputer(id.id);
                assert computer != null;
                if (computer.getMachine() == null) {
                    computer.initializeMachine(index, archetypeChunk, store);
                    computer.getMachine().start(computer);
                } else {
                    computer.tick(dt);
                }
            }

            @NullableDecl
            @Override
            public Query<ChunkStore> getQuery() {
                return Query.and(this.computerOnComponentType, this.computerIdComponentType);
            }
        }
    }

    public static class EntitySystems {
        public static class ComputerHolderSystem extends HolderSystem<EntityStore> {
            private final ComponentType<EntityStore, ComputerId.Entity> computerIdComponentType;
            private final ComponentType<EntityStore, ComputerLanguage.Entity> computerLanguageComponentType;

            public ComputerHolderSystem(
                    ComponentType<EntityStore, ComputerId.Entity> computerIdComponentType,
                    ComponentType<EntityStore, ComputerLanguage.Entity> computerLanguageComponentType
            ) {
                this.computerIdComponentType = computerIdComponentType;
                this.computerLanguageComponentType = computerLanguageComponentType;
            }

            @Override
            public void onEntityAdd(@NonNullDecl Holder<EntityStore> holder, @NonNullDecl AddReason addReason, @NonNullDecl Store<EntityStore> store) {
                ComputerId.Entity id = holder.getComponent(this.computerIdComponentType);
                ComputerLanguage.Entity language = holder.getComponent(this.computerLanguageComponentType);
                assert id != null;
                assert language != null;
                Computer computer = new Computer(id.id, language.machine);
                ComputationAPI.addComputer(computer);
            }

            @Override
            public void onEntityRemoved(@NonNullDecl Holder<EntityStore> holder, @NonNullDecl RemoveReason removeReason, @NonNullDecl Store<EntityStore> store) {
                ComputerId.Entity id = holder.getComponent(this.computerIdComponentType);
                assert id != null;
                Computer computer = ComputationAPI.getComputer(id.id);
                assert computer != null;
                assert computer.getMachine() != null;
                computer.getMachine().stop(computer);
                ComputationAPI.removeComputer(id.id);
            }

            @NullableDecl
            @Override
            public Query<EntityStore> getQuery() {
                return Query.and(this.computerIdComponentType, this.computerLanguageComponentType);
            }
        }

        public static class ComputerTickingSystem extends EntityTickingSystem<EntityStore> {
            private final ComponentType<EntityStore, ComputerOn.Entity> computerOnComponentType;
            private final ComponentType<EntityStore, ComputerId.Entity> computerIdComponentType;

            public ComputerTickingSystem(
                    ComponentType<EntityStore, ComputerOn.Entity> computerOnComponentType,
                    ComponentType<EntityStore, ComputerId.Entity> computerIdComponentType
            ) {
                this.computerOnComponentType = computerOnComponentType;
                this.computerIdComponentType = computerIdComponentType;
            }

            @Override
            public void tick(float dt, int index, @NonNullDecl ArchetypeChunk<EntityStore> archetypeEntity, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
                ComputerId.Entity id = archetypeEntity.getComponent(index, this.computerIdComponentType);
                assert id != null;
                Computer computer = ComputationAPI.getComputer(id.id);
                assert computer != null;
                if (computer.getMachine() == null) {
                    computer.initializeMachine(index, archetypeEntity, store);
                    computer.getMachine().start(computer);
                } else {
                    computer.tick(dt);
                }
            }

            @NullableDecl
            @Override
            public Query<EntityStore> getQuery() {
                return Query.and(this.computerOnComponentType, this.computerIdComponentType);
            }
        }
    }
}
