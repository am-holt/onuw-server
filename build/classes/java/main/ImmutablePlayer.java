

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link Player}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutablePlayer.builder()}.
 */
@Generated(from = "Player", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
public final class ImmutablePlayer implements Player {
  private final String id;
  private final String name;
  private final String lastKnownRole;

  private ImmutablePlayer(String id, String name, String lastKnownRole) {
    this.id = id;
    this.name = name;
    this.lastKnownRole = lastKnownRole;
  }

  /**
   * @return The value of the {@code id} attribute
   */
  @JsonProperty("id")
  @Override
  public String id() {
    return id;
  }

  /**
   * @return The value of the {@code name} attribute
   */
  @JsonProperty("name")
  @Override
  public String name() {
    return name;
  }

  /**
   * @return The value of the {@code lastKnownRole} attribute
   */
  @JsonProperty("lastKnownRole")
  @Override
  public String lastKnownRole() {
    return lastKnownRole;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Player#id() id} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for id
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePlayer withId(String value) {
    String newValue = Objects.requireNonNull(value, "id");
    if (this.id.equals(newValue)) return this;
    return new ImmutablePlayer(newValue, this.name, this.lastKnownRole);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Player#name() name} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for name
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePlayer withName(String value) {
    String newValue = Objects.requireNonNull(value, "name");
    if (this.name.equals(newValue)) return this;
    return new ImmutablePlayer(this.id, newValue, this.lastKnownRole);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Player#lastKnownRole() lastKnownRole} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for lastKnownRole
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePlayer withLastKnownRole(String value) {
    String newValue = Objects.requireNonNull(value, "lastKnownRole");
    if (this.lastKnownRole.equals(newValue)) return this;
    return new ImmutablePlayer(this.id, this.name, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutablePlayer} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePlayer
        && equalTo((ImmutablePlayer) another);
  }

  private boolean equalTo(ImmutablePlayer another) {
    return id.equals(another.id)
        && name.equals(another.name)
        && lastKnownRole.equals(another.lastKnownRole);
  }

  /**
   * Computes a hash code from attributes: {@code id}, {@code name}, {@code lastKnownRole}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + id.hashCode();
    h += (h << 5) + name.hashCode();
    h += (h << 5) + lastKnownRole.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code Player} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Player")
        .omitNullValues()
        .add("id", id)
        .add("name", name)
        .add("lastKnownRole", lastKnownRole)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "Player", generator = "Immutables")
  @Deprecated
  @SuppressWarnings("Immutable")
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements Player {
    @Nullable String id;
    @Nullable String name;
    @Nullable String lastKnownRole;
    @JsonProperty("id")
    public void setId(String id) {
      this.id = id;
    }
    @JsonProperty("name")
    public void setName(String name) {
      this.name = name;
    }
    @JsonProperty("lastKnownRole")
    public void setLastKnownRole(String lastKnownRole) {
      this.lastKnownRole = lastKnownRole;
    }
    @Override
    public String id() { throw new UnsupportedOperationException(); }
    @Override
    public String name() { throw new UnsupportedOperationException(); }
    @Override
    public String lastKnownRole() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutablePlayer fromJson(Json json) {
    ImmutablePlayer.Builder builder = ImmutablePlayer.builder();
    if (json.id != null) {
      builder.id(json.id);
    }
    if (json.name != null) {
      builder.name(json.name);
    }
    if (json.lastKnownRole != null) {
      builder.lastKnownRole(json.lastKnownRole);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link Player} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Player instance
   */
  public static ImmutablePlayer copyOf(Player instance) {
    if (instance instanceof ImmutablePlayer) {
      return (ImmutablePlayer) instance;
    }
    return ImmutablePlayer.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutablePlayer ImmutablePlayer}.
   * @return A new ImmutablePlayer builder
   */
  public static ImmutablePlayer.Builder builder() {
    return new ImmutablePlayer.Builder();
  }

  /**
   * Builds instances of type {@link ImmutablePlayer ImmutablePlayer}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Player", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_ID = 0x1L;
    private static final long INIT_BIT_NAME = 0x2L;
    private static final long INIT_BIT_LAST_KNOWN_ROLE = 0x4L;
    private long initBits = 0x7L;

    private @Nullable String id;
    private @Nullable String name;
    private @Nullable String lastKnownRole;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code Player} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(Player instance) {
      Objects.requireNonNull(instance, "instance");
      id(instance.id());
      name(instance.name());
      lastKnownRole(instance.lastKnownRole());
      return this;
    }

    /**
     * Initializes the value for the {@link Player#id() id} attribute.
     * @param id The value for id 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("id")
    public final Builder id(String id) {
      this.id = Objects.requireNonNull(id, "id");
      initBits &= ~INIT_BIT_ID;
      return this;
    }

    /**
     * Initializes the value for the {@link Player#name() name} attribute.
     * @param name The value for name 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("name")
    public final Builder name(String name) {
      this.name = Objects.requireNonNull(name, "name");
      initBits &= ~INIT_BIT_NAME;
      return this;
    }

    /**
     * Initializes the value for the {@link Player#lastKnownRole() lastKnownRole} attribute.
     * @param lastKnownRole The value for lastKnownRole 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("lastKnownRole")
    public final Builder lastKnownRole(String lastKnownRole) {
      this.lastKnownRole = Objects.requireNonNull(lastKnownRole, "lastKnownRole");
      initBits &= ~INIT_BIT_LAST_KNOWN_ROLE;
      return this;
    }

    /**
     * Builds a new {@link ImmutablePlayer ImmutablePlayer}.
     * @return An immutable instance of Player
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutablePlayer build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePlayer(id, name, lastKnownRole);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_ID) != 0) attributes.add("id");
      if ((initBits & INIT_BIT_NAME) != 0) attributes.add("name");
      if ((initBits & INIT_BIT_LAST_KNOWN_ROLE) != 0) attributes.add("lastKnownRole");
      return "Cannot build Player, some of required attributes are not set " + attributes;
    }
  }
}
