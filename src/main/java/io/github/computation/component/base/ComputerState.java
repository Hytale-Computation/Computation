package io.github.computation.component.base;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;
import com.hypixel.hytale.component.Component;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class ComputerState<ECS_TYPE> implements Component<ECS_TYPE> {
    private State state = State.OFF;

    public static final Codec<ComputerState> CODEC = BuilderCodec.builder(ComputerState.class, ComputerState::new)
            .append(
                    new KeyedCodec<>("State", new EnumCodec<>(State.class)),
                    (comp, state) -> comp.state = state,
                    ComputerState::getState
            ).documentation("Determines the State of the ComputerComponent.").add()
            .build();

    public ComputerState() {}

    @NonNullDecl
    public State getState() {
        return this.state;
    }

    @NullableDecl
    @Override
    public ComputerState<ECS_TYPE> clone() {
        ComputerState<ECS_TYPE> component = new ComputerState<>();
        component.state = this.state;
        return component;
    }

    public enum State {
        ON,
        OFF
    }
}
