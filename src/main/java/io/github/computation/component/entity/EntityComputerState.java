package io.github.computation.component.entity;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import io.github.computation.component.base.ComputerState;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class EntityComputerState extends ComputerState<EntityStore> {
    private static ComponentType<EntityStore, EntityComputerState> TYPE;

    public static ComponentType<EntityStore, EntityComputerState> componentType() {
        return TYPE;
    }

    public static void setComponentType(@NonNullDecl ComponentType<EntityStore, EntityComputerState> componentType) {
        TYPE = componentType;
    }
}
