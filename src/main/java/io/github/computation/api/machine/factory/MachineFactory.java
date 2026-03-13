package io.github.computation.api.machine.factory;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.world.WorldProvider;
import io.github.computation.api.machine.BaseMachine;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

@FunctionalInterface
public interface MachineFactory<ECS_TYPE extends WorldProvider> {
    @NonNullDecl
    BaseMachine createMachine(Ref<ECS_TYPE> ref, Store<ECS_TYPE> store, CommandBuffer<ECS_TYPE> commandBuffer);
}