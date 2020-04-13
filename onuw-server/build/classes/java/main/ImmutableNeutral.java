

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
 * Immutable implementation of {@link Neutral}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableNeutral.builder()}.
 */
@Generated(from = "Neutral", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
public final class ImmutableNeutral implements Neutral {
  private final int id;
  private final String lastKnownRole;

  private ImmutableNeutral(int id, String lastKnownRole) {
    this.id = id;
    this.lastKnownRole = lastKnownRole;
  }

  /**
   * @return The value of the {@code id} attribute
   */
  @JsonProperty("id")
  @Override
  public int id() {
    return id;
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
   * Copy the current immutable object by setting a value for the {@link Neutral#id() id} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for id
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableNeutral withId(int value) {
    if (this.id == value) return this;
    return new ImmutableNeutral(value, this.lastKnownRole);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Neutral#lastKnownRole() lastKnownRole} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for lastKnownRole
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableNeutral withLastKnownRole(String value) {
    String newValue = Objects.requireNonNull(value, "lastKnownRole");
    if (this.lastKnownRole.equals(newValue)) return this;
    return new ImmutableNeutral(this.id, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableNeutral} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableNeutral
        && equalTo((ImmutableNeutral) another);
  }

  private boolean equalTo(ImmutableNeutral another) {
    return id == another.id
        && lastKnownRole.equals(another.lastKnownRole);
  }

  /**
   * Computes a hash code from attributes: {@code id}, {@code lastKnownRole}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + id;
    h += (h << 5) + lastKnownRole.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code Neutral} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Neutral")
        .omitNullValues()
        .add("id", id)
        .add("lastKnownRole", lastKnownRole)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "Neutral", generator = "Immutables")
  @Deprecated
  @SuppressWarnings("Immutable")
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements Neutral {
    int id;
    boolean idIsSet;
    @Nullable String lastKnownRole;
    @JsonProperty("id")
    public void setId(int id) {
      this.id = id;
      this.idIsSet = true;
    }
    @JsonProperty("lastKnownRole")
    public void setLastKnownRole(String lastKnownRole) {
      this.lastKnownRole = lastKnownRole;
    }
    @Override
    public int id() { throw new UnsupportedOperationException(); }
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
  static ImmutableNeutral fromJson(Json json) {
    ImmutableNeutral.Builder builder = ImmutableNeutral.builder();
    if (json.idIsSet) {
      builder.id(json.id);
    }
    if (json.lastKnownRole != null) {
      builder.lastKnownRole(json.lastKnownRole);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link Neutral} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Neutral instance
   */
  public static ImmutableNeutral copyOf(Neutral instance) {
    if (instance instanceof ImmutableNeutral) {
      return (ImmutableNeutral) instance;
    }
    return ImmutableNeutral.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableNeutral ImmutableNeutral}.
   * @return A new ImmutableNeutral builder
   */
  public static ImmutableNeutral.Builder builder() {
    return new ImmutableNeutral.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableNeutral ImmutableNeutral}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Neutral", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_ID = 0x1L;
    private static final long INIT_BIT_LAST_KNOWN_ROLE = 0x2L;
    private long initBits = 0x3L;

    private int id;
    private @Nullable String lastKnownRole;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code Neutral} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(Neutral instance) {
      Objects.requireNonNull(instance, "instance");
      id(instance.id());
      lastKnownRole(instance.lastKnownRole());
      return this;
    }

    /**
     * Initializes the value for the {@link Neutral#id() id} attribute.
     * @param id The value for id 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("id")
    public final Builder id(int id) {
      this.id = id;
      initBits &= ~INIT_BIT_ID;
      return this;
    }

    /**
     * Initializes the value for the {@link Neutral#lastKnownRole() lastKnownRole} attribute.
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
     * Builds a new {@link ImmutableNeutral ImmutableNeutral}.
     * @return An immutable instance of Neutral
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableNeutral build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableNeutral(id, lastKnownRole);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_ID) != 0) attributes.add("id");
      if ((initBits & INIT_BIT_LAST_KNOWN_ROLE) != 0) attributes.add("lastKnownRole");
      return "Cannot build Neutral, some of required attributes are not set " + attributes;
    }
  }
}
