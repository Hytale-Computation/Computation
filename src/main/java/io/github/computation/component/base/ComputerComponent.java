package io.github.computation.component.base;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Component;
import io.github.computation.Computation;
import io.github.computation.api.machine.BaseMachine;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.UUID;

public class ComputerComponent<ECS_TYPE> implements Component<ECS_TYPE> {
    private UUID id;
    private String language;

    public static final Codec<ComputerComponent> CODEC = BuilderCodec.builder(ComputerComponent.class, ComputerComponent::new)
            .append(
                    new KeyedCodec<>("Id", Codec.UUID_STRING),
                    (comp, id) -> comp.id = id,
                    comp -> comp.id
            ).documentation("The ComputerComponent's UUID.").add()
            .append(
                    new KeyedCodec<>("Language", Codec.STRING),
                    (comp, language) -> comp.language = language,
                    comp -> comp.language
            ).documentation("Determines the Language and its Machine for the ComputerComponent.").add()
            .build();

    public ComputerComponent() {}

    @NullableDecl
    public String getLanguage() {
        return this.language;
    }

    @NonNullDecl
    public UUID getId() {
        return this.id;
    }

    @NullableDecl
    @Override
    public ComputerComponent<ECS_TYPE> clone() {
        ComputerComponent<ECS_TYPE> component = new ComputerComponent<>();
        component.language = this.language;
        return component;
    }
}
