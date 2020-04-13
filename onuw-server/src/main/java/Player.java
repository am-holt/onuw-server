import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value.Immutable;

@Immutable
@JsonSerialize(as = ImmutablePlayer.class)
@JsonDeserialize(as = ImmutablePlayer.class)
public interface Player {
    public String id();
    public String name();
    public String lastKnownRole();

    static Player of(String id, String name, String role) {
        return ImmutablePlayer.builder().id(id).name(name).lastKnownRole(role).build();
    }
}
