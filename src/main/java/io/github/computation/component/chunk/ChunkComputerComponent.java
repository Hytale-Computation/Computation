package io.github.computation.component.chunk;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import io.github.computation.component.base.ComputerComponent;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class ChunkComputerComponent extends ComputerComponent<ChunkStore> {
    private static ComponentType<ChunkStore, ChunkComputerComponent> TYPE;

    public static ComponentType<ChunkStore, ChunkComputerComponent> componentType() {
        return TYPE;
    }

    public static void setComponentType(@NonNullDecl ComponentType<ChunkStore, ChunkComputerComponent> componentType) {
        TYPE = componentType;
    }
}
