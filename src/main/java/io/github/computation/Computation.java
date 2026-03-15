package io.github.computation;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import io.github.computation.component.ComputerId;
import io.github.computation.component.ComputerLanguage;
import io.github.computation.component.ComputerOn;
import io.github.computation.systems.ComputerSystems;

import javax.annotation.Nonnull;

public class Computation extends JavaPlugin {
    private static Computation INSTANCE;

    public ComponentType<ChunkStore, ComputerOn.Chunk> computerOnChunkComponentType;
    public ComponentType<EntityStore, ComputerOn.Entity> computerOnEntityComponentType;
    public ComponentType<ChunkStore, ComputerId.Chunk> computerIdChunkComponentType;
    public ComponentType<EntityStore, ComputerId.Entity> computerIdEntityComponentType;
    public ComponentType<ChunkStore, ComputerLanguage.Chunk> computerLanguageChunkComponentType;
    public ComponentType<EntityStore, ComputerLanguage.Entity> computerLanguageEntityComponentType;

    public Computation(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
    }

    public static Computation get() {
        return INSTANCE;
    }

    @Override
    protected void setup() {
        this.computerOnChunkComponentType = this.getChunkStoreRegistry().registerComponent(ComputerOn.Chunk.class, "ComputerOn", ComputerOn.Chunk.CODEC);
        this.computerOnEntityComponentType = this.getEntityStoreRegistry().registerComponent(ComputerOn.Entity.class, "ComputerOn", ComputerOn.Entity.CODEC);
        this.computerIdChunkComponentType = this.getChunkStoreRegistry().registerComponent(ComputerId.Chunk.class, "ComputerId", ComputerId.Chunk.CODEC);
        this.computerIdEntityComponentType = this.getEntityStoreRegistry().registerComponent(ComputerId.Entity.class, "ComputerId", ComputerId.Entity.CODEC);
        this.computerLanguageChunkComponentType = this.getChunkStoreRegistry().registerComponent(ComputerLanguage.Chunk.class, "ComputerLanguage", ComputerLanguage.Chunk.CODEC);
        this.computerLanguageEntityComponentType = this.getEntityStoreRegistry().registerComponent(ComputerLanguage.Entity.class, "ComputerLanguage", ComputerLanguage.Entity.CODEC);

        this.getChunkStoreRegistry().registerSystem(new ComputerSystems.ChunkSystems.ComputerHolderSystem(this.computerIdChunkComponentType, this.computerLanguageChunkComponentType));
        this.getEntityStoreRegistry().registerSystem(new ComputerSystems.EntitySystems.ComputerHolderSystem(this.computerIdEntityComponentType, this.computerLanguageEntityComponentType));
        this.getChunkStoreRegistry().registerSystem(new ComputerSystems.ChunkSystems.ComputerTickingSystem(this.computerOnChunkComponentType, this.computerIdChunkComponentType));
        this.getEntityStoreRegistry().registerSystem(new ComputerSystems.EntitySystems.ComputerTickingSystem(this.computerOnEntityComponentType, this.computerIdEntityComponentType));
    }
}