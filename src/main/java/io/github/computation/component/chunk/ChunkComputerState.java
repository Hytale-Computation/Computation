package io.github.computation.component.chunk;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import io.github.computation.component.base.ComputerState;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class ChunkComputerState extends ComputerState<ChunkStore> {
    private static ComponentType<ChunkStore, ChunkComputerState> TYPE;

    public static ComponentType<ChunkStore, ChunkComputerState> componentType() {
        return TYPE;
    }

    public static void setComponentType(@NonNullDecl ComponentType<ChunkStore, ChunkComputerState> componentType) {
        TYPE = componentType;
    }
}
