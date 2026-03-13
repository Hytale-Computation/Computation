package io.github.computation.component.entity;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import io.github.computation.component.base.ComputerComponent;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class EntityComputerComponent extends ComputerComponent<EntityStore> {
    private static ComponentType<EntityStore, EntityComputerComponent> TYPE;

    public static ComponentType<EntityStore, EntityComputerComponent> componentType() {
        return TYPE;
    }

    public static void setComponentType(@NonNullDecl ComponentType<EntityStore, EntityComputerComponent> componentType) {
        TYPE = componentType;
    }
}
