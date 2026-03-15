package io.github.computation.api.machine.factory;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.world.WorldProvider;
import io.github.computation.api.machine.BaseMachine;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@FunctionalInterface
public interface MachineFactory<ECS_TYPE> {
    @NonNullDecl
    BaseMachine createMachine(int index, @NonNullDecl ArchetypeChunk<ECS_TYPE> archetypeChunk, @NonNullDecl Store<ECS_TYPE> store);
}