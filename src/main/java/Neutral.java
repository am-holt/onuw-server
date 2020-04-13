import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value.Immutable;

@Immutable
@JsonSerialize(as = ImmutableNeutral.class)
@JsonDeserialize(as = ImmutableNeutral.class)
public interface Neutral {
    public int id();
    public String lastKnownRole();

    static Neutral of(int id, String role) {
        return ImmutableNeutral.builder().id(id).lastKnownRole(role).build();
    }
}
