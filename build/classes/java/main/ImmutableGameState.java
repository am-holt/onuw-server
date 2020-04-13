

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
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
 * Immutable implementation of {@link GameState}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableGameState.builder()}.
 */
@Generated(from = "GameState", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
public final class ImmutableGameState implements GameState {
  private final ImmutableList<Player> otherPlayers;
  private final ImmutableList<Neutral> neutralCards;
  private final Player currentPlayer;
  private final ImmutableList<String> availableRoles;
  private final String gamePhase;
  private final int timeLeft;
  private final int currentVote;
  private final int gameId;

  private ImmutableGameState(
      ImmutableList<Player> otherPlayers,
      ImmutableList<Neutral> neutralCards,
      Player currentPlayer,
      ImmutableList<String> availableRoles,
      String gamePhase,
      int timeLeft,
      int currentVote,
      int gameId) {
    this.otherPlayers = otherPlayers;
    this.neutralCards = neutralCards;
    this.currentPlayer = currentPlayer;
    this.availableRoles = availableRoles;
    this.gamePhase = gamePhase;
    this.timeLeft = timeLeft;
    this.currentVote = currentVote;
    this.gameId = gameId;
  }

  /**
   * @return The value of the {@code otherPlayers} attribute
   */
  @JsonProperty("otherPlayers")
  @Override
  public ImmutableList<Player> otherPlayers() {
    return otherPlayers;
  }

  /**
   * @return The value of the {@code neutralCards} attribute
   */
  @JsonProperty("neutralCards")
  @Override
  public ImmutableList<Neutral> neutralCards() {
    return neutralCards;
  }

  /**
   * @return The value of the {@code currentPlayer} attribute
   */
  @JsonProperty("currentPlayer")
  @Override
  public Player currentPlayer() {
    return currentPlayer;
  }

  /**
   * @return The value of the {@code availableRoles} attribute
   */
  @JsonProperty("availableRoles")
  @Override
  public ImmutableList<String> availableRoles() {
    return availableRoles;
  }

  /**
   * @return The value of the {@code gamePhase} attribute
   */
  @JsonProperty("gamePhase")
  @Override
  public String gamePhase() {
    return gamePhase;
  }

  /**
   * @return The value of the {@code timeLeft} attribute
   */
  @JsonProperty("timeLeft")
  @Override
  public int timeLeft() {
    return timeLeft;
  }

  /**
   * @return The value of the {@code currentVote} attribute
   */
  @JsonProperty("currentVote")
  @Override
  public int currentVote() {
    return currentVote;
  }

  /**
   * @return The value of the {@code gameId} attribute
   */
  @JsonProperty("gameId")
  @Override
  public int gameId() {
    return gameId;
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link GameState#otherPlayers() otherPlayers}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableGameState withOtherPlayers(Player... elements) {
    ImmutableList<Player> newValue = ImmutableList.copyOf(elements);
    return new ImmutableGameState(
        newValue,
        this.neutralCards,
        this.currentPlayer,
        this.availableRoles,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link GameState#otherPlayers() otherPlayers}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of otherPlayers elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableGameState withOtherPlayers(Iterable<? extends Player> elements) {
    if (this.otherPlayers == elements) return this;
    ImmutableList<Player> newValue = ImmutableList.copyOf(elements);
    return new ImmutableGameState(
        newValue,
        this.neutralCards,
        this.currentPlayer,
        this.availableRoles,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link GameState#neutralCards() neutralCards}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableGameState withNeutralCards(Neutral... elements) {
    ImmutableList<Neutral> newValue = ImmutableList.copyOf(elements);
    return new ImmutableGameState(
        this.otherPlayers,
        newValue,
        this.currentPlayer,
        this.availableRoles,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link GameState#neutralCards() neutralCards}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of neutralCards elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableGameState withNeutralCards(Iterable<? extends Neutral> elements) {
    if (this.neutralCards == elements) return this;
    ImmutableList<Neutral> newValue = ImmutableList.copyOf(elements);
    return new ImmutableGameState(
        this.otherPlayers,
        newValue,
        this.currentPlayer,
        this.availableRoles,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link GameState#currentPlayer() currentPlayer} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for currentPlayer
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableGameState withCurrentPlayer(Player value) {
    if (this.currentPlayer == value) return this;
    Player newValue = Objects.requireNonNull(value, "currentPlayer");
    return new ImmutableGameState(
        this.otherPlayers,
        this.neutralCards,
        newValue,
        this.availableRoles,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link GameState#availableRoles() availableRoles}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableGameState withAvailableRoles(String... elements) {
    ImmutableList<String> newValue = ImmutableList.copyOf(elements);
    return new ImmutableGameState(
        this.otherPlayers,
        this.neutralCards,
        this.currentPlayer,
        newValue,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link GameState#availableRoles() availableRoles}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of availableRoles elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableGameState withAvailableRoles(Iterable<String> elements) {
    if (this.availableRoles == elements) return this;
    ImmutableList<String> newValue = ImmutableList.copyOf(elements);
    return new ImmutableGameState(
        this.otherPlayers,
        this.neutralCards,
        this.currentPlayer,
        newValue,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link GameState#gamePhase() gamePhase} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for gamePhase
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableGameState withGamePhase(String value) {
    String newValue = Objects.requireNonNull(value, "gamePhase");
    if (this.gamePhase.equals(newValue)) return this;
    return new ImmutableGameState(
        this.otherPlayers,
        this.neutralCards,
        this.currentPlayer,
        this.availableRoles,
        newValue,
        this.timeLeft,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link GameState#timeLeft() timeLeft} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for timeLeft
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableGameState withTimeLeft(int value) {
    if (this.timeLeft == value) return this;
    return new ImmutableGameState(
        this.otherPlayers,
        this.neutralCards,
        this.currentPlayer,
        this.availableRoles,
        this.gamePhase,
        value,
        this.currentVote,
        this.gameId);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link GameState#currentVote() currentVote} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for currentVote
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableGameState withCurrentVote(int value) {
    if (this.currentVote == value) return this;
    return new ImmutableGameState(
        this.otherPlayers,
        this.neutralCards,
        this.currentPlayer,
        this.availableRoles,
        this.gamePhase,
        this.timeLeft,
        value,
        this.gameId);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link GameState#gameId() gameId} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for gameId
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableGameState withGameId(int value) {
    if (this.gameId == value) return this;
    return new ImmutableGameState(
        this.otherPlayers,
        this.neutralCards,
        this.currentPlayer,
        this.availableRoles,
        this.gamePhase,
        this.timeLeft,
        this.currentVote,
        value);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableGameState} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableGameState
        && equalTo((ImmutableGameState) another);
  }

  private boolean equalTo(ImmutableGameState another) {
    return otherPlayers.equals(another.otherPlayers)
        && neutralCards.equals(another.neutralCards)
        && currentPlayer.equals(another.currentPlayer)
        && availableRoles.equals(another.availableRoles)
        && gamePhase.equals(another.gamePhase)
        && timeLeft == another.timeLeft
        && currentVote == another.currentVote
        && gameId == another.gameId;
  }

  /**
   * Computes a hash code from attributes: {@code otherPlayers}, {@code neutralCards}, {@code currentPlayer}, {@code availableRoles}, {@code gamePhase}, {@code timeLeft}, {@code currentVote}, {@code gameId}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + otherPlayers.hashCode();
    h += (h << 5) + neutralCards.hashCode();
    h += (h << 5) + currentPlayer.hashCode();
    h += (h << 5) + availableRoles.hashCode();
    h += (h << 5) + gamePhase.hashCode();
    h += (h << 5) + timeLeft;
    h += (h << 5) + currentVote;
    h += (h << 5) + gameId;
    return h;
  }

  /**
   * Prints the immutable value {@code GameState} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("GameState")
        .omitNullValues()
        .add("otherPlayers", otherPlayers)
        .add("neutralCards", neutralCards)
        .add("currentPlayer", currentPlayer)
        .add("availableRoles", availableRoles)
        .add("gamePhase", gamePhase)
        .add("timeLeft", timeLeft)
        .add("currentVote", currentVote)
        .add("gameId", gameId)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "GameState", generator = "Immutables")
  @Deprecated
  @SuppressWarnings("Immutable")
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements GameState {
    @Nullable List<Player> otherPlayers = ImmutableList.of();
    @Nullable List<Neutral> neutralCards = ImmutableList.of();
    @Nullable Player currentPlayer;
    @Nullable List<String> availableRoles = ImmutableList.of();
    @Nullable String gamePhase;
    int timeLeft;
    boolean timeLeftIsSet;
    int currentVote;
    boolean currentVoteIsSet;
    int gameId;
    boolean gameIdIsSet;
    @JsonProperty("otherPlayers")
    public void setOtherPlayers(List<Player> otherPlayers) {
      this.otherPlayers = otherPlayers;
    }
    @JsonProperty("neutralCards")
    public void setNeutralCards(List<Neutral> neutralCards) {
      this.neutralCards = neutralCards;
    }
    @JsonProperty("currentPlayer")
    public void setCurrentPlayer(Player currentPlayer) {
      this.currentPlayer = currentPlayer;
    }
    @JsonProperty("availableRoles")
    public void setAvailableRoles(List<String> availableRoles) {
      this.availableRoles = availableRoles;
    }
    @JsonProperty("gamePhase")
    public void setGamePhase(String gamePhase) {
      this.gamePhase = gamePhase;
    }
    @JsonProperty("timeLeft")
    public void setTimeLeft(int timeLeft) {
      this.timeLeft = timeLeft;
      this.timeLeftIsSet = true;
    }
    @JsonProperty("currentVote")
    public void setCurrentVote(int currentVote) {
      this.currentVote = currentVote;
      this.currentVoteIsSet = true;
    }
    @JsonProperty("gameId")
    public void setGameId(int gameId) {
      this.gameId = gameId;
      this.gameIdIsSet = true;
    }
    @Override
    public List<Player> otherPlayers() { throw new UnsupportedOperationException(); }
    @Override
    public List<Neutral> neutralCards() { throw new UnsupportedOperationException(); }
    @Override
    public Player currentPlayer() { throw new UnsupportedOperationException(); }
    @Override
    public List<String> availableRoles() { throw new UnsupportedOperationException(); }
    @Override
    public String gamePhase() { throw new UnsupportedOperationException(); }
    @Override
    public int timeLeft() { throw new UnsupportedOperationException(); }
    @Override
    public int currentVote() { throw new UnsupportedOperationException(); }
    @Override
    public int gameId() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableGameState fromJson(Json json) {
    ImmutableGameState.Builder builder = ImmutableGameState.builder();
    if (json.otherPlayers != null) {
      builder.addAllOtherPlayers(json.otherPlayers);
    }
    if (json.neutralCards != null) {
      builder.addAllNeutralCards(json.neutralCards);
    }
    if (json.currentPlayer != null) {
      builder.currentPlayer(json.currentPlayer);
    }
    if (json.availableRoles != null) {
      builder.addAllAvailableRoles(json.availableRoles);
    }
    if (json.gamePhase != null) {
      builder.gamePhase(json.gamePhase);
    }
    if (json.timeLeftIsSet) {
      builder.timeLeft(json.timeLeft);
    }
    if (json.currentVoteIsSet) {
      builder.currentVote(json.currentVote);
    }
    if (json.gameIdIsSet) {
      builder.gameId(json.gameId);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link GameState} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable GameState instance
   */
  public static ImmutableGameState copyOf(GameState instance) {
    if (instance instanceof ImmutableGameState) {
      return (ImmutableGameState) instance;
    }
    return ImmutableGameState.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableGameState ImmutableGameState}.
   * @return A new ImmutableGameState builder
   */
  public static ImmutableGameState.Builder builder() {
    return new ImmutableGameState.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableGameState ImmutableGameState}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "GameState", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_CURRENT_PLAYER = 0x1L;
    private static final long INIT_BIT_GAME_PHASE = 0x2L;
    private static final long INIT_BIT_TIME_LEFT = 0x4L;
    private static final long INIT_BIT_CURRENT_VOTE = 0x8L;
    private static final long INIT_BIT_GAME_ID = 0x10L;
    private long initBits = 0x1fL;

    private ImmutableList.Builder<Player> otherPlayers = ImmutableList.builder();
    private ImmutableList.Builder<Neutral> neutralCards = ImmutableList.builder();
    private @Nullable Player currentPlayer;
    private ImmutableList.Builder<String> availableRoles = ImmutableList.builder();
    private @Nullable String gamePhase;
    private int timeLeft;
    private int currentVote;
    private int gameId;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code GameState} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * Collection elements and entries will be added, not replaced.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(GameState instance) {
      Objects.requireNonNull(instance, "instance");
      addAllOtherPlayers(instance.otherPlayers());
      addAllNeutralCards(instance.neutralCards());
      currentPlayer(instance.currentPlayer());
      addAllAvailableRoles(instance.availableRoles());
      gamePhase(instance.gamePhase());
      timeLeft(instance.timeLeft());
      currentVote(instance.currentVote());
      gameId(instance.gameId());
      return this;
    }

    /**
     * Adds one element to {@link GameState#otherPlayers() otherPlayers} list.
     * @param element A otherPlayers element
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addOtherPlayers(Player element) {
      this.otherPlayers.add(element);
      return this;
    }

    /**
     * Adds elements to {@link GameState#otherPlayers() otherPlayers} list.
     * @param elements An array of otherPlayers elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addOtherPlayers(Player... elements) {
      this.otherPlayers.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link GameState#otherPlayers() otherPlayers} list.
     * @param elements An iterable of otherPlayers elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("otherPlayers")
    public final Builder otherPlayers(Iterable<? extends Player> elements) {
      this.otherPlayers = ImmutableList.builder();
      return addAllOtherPlayers(elements);
    }

    /**
     * Adds elements to {@link GameState#otherPlayers() otherPlayers} list.
     * @param elements An iterable of otherPlayers elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addAllOtherPlayers(Iterable<? extends Player> elements) {
      this.otherPlayers.addAll(elements);
      return this;
    }

    /**
     * Adds one element to {@link GameState#neutralCards() neutralCards} list.
     * @param element A neutralCards element
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addNeutralCards(Neutral element) {
      this.neutralCards.add(element);
      return this;
    }

    /**
     * Adds elements to {@link GameState#neutralCards() neutralCards} list.
     * @param elements An array of neutralCards elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addNeutralCards(Neutral... elements) {
      this.neutralCards.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link GameState#neutralCards() neutralCards} list.
     * @param elements An iterable of neutralCards elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("neutralCards")
    public final Builder neutralCards(Iterable<? extends Neutral> elements) {
      this.neutralCards = ImmutableList.builder();
      return addAllNeutralCards(elements);
    }

    /**
     * Adds elements to {@link GameState#neutralCards() neutralCards} list.
     * @param elements An iterable of neutralCards elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addAllNeutralCards(Iterable<? extends Neutral> elements) {
      this.neutralCards.addAll(elements);
      return this;
    }

    /**
     * Initializes the value for the {@link GameState#currentPlayer() currentPlayer} attribute.
     * @param currentPlayer The value for currentPlayer 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("currentPlayer")
    public final Builder currentPlayer(Player currentPlayer) {
      this.currentPlayer = Objects.requireNonNull(currentPlayer, "currentPlayer");
      initBits &= ~INIT_BIT_CURRENT_PLAYER;
      return this;
    }

    /**
     * Adds one element to {@link GameState#availableRoles() availableRoles} list.
     * @param element A availableRoles element
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addAvailableRoles(String element) {
      this.availableRoles.add(element);
      return this;
    }

    /**
     * Adds elements to {@link GameState#availableRoles() availableRoles} list.
     * @param elements An array of availableRoles elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addAvailableRoles(String... elements) {
      this.availableRoles.add(elements);
      return this;
    }


    /**
     * Sets or replaces all elements for {@link GameState#availableRoles() availableRoles} list.
     * @param elements An iterable of availableRoles elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("availableRoles")
    public final Builder availableRoles(Iterable<String> elements) {
      this.availableRoles = ImmutableList.builder();
      return addAllAvailableRoles(elements);
    }

    /**
     * Adds elements to {@link GameState#availableRoles() availableRoles} list.
     * @param elements An iterable of availableRoles elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addAllAvailableRoles(Iterable<String> elements) {
      this.availableRoles.addAll(elements);
      return this;
    }

    /**
     * Initializes the value for the {@link GameState#gamePhase() gamePhase} attribute.
     * @param gamePhase The value for gamePhase 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("gamePhase")
    public final Builder gamePhase(String gamePhase) {
      this.gamePhase = Objects.requireNonNull(gamePhase, "gamePhase");
      initBits &= ~INIT_BIT_GAME_PHASE;
      return this;
    }

    /**
     * Initializes the value for the {@link GameState#timeLeft() timeLeft} attribute.
     * @param timeLeft The value for timeLeft 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("timeLeft")
    public final Builder timeLeft(int timeLeft) {
      this.timeLeft = timeLeft;
      initBits &= ~INIT_BIT_TIME_LEFT;
      return this;
    }

    /**
     * Initializes the value for the {@link GameState#currentVote() currentVote} attribute.
     * @param currentVote The value for currentVote 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("currentVote")
    public final Builder currentVote(int currentVote) {
      this.currentVote = currentVote;
      initBits &= ~INIT_BIT_CURRENT_VOTE;
      return this;
    }

    /**
     * Initializes the value for the {@link GameState#gameId() gameId} attribute.
     * @param gameId The value for gameId 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("gameId")
    public final Builder gameId(int gameId) {
      this.gameId = gameId;
      initBits &= ~INIT_BIT_GAME_ID;
      return this;
    }

    /**
     * Builds a new {@link ImmutableGameState ImmutableGameState}.
     * @return An immutable instance of GameState
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableGameState build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableGameState(
          otherPlayers.build(),
          neutralCards.build(),
          currentPlayer,
          availableRoles.build(),
          gamePhase,
          timeLeft,
          currentVote,
          gameId);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_CURRENT_PLAYER) != 0) attributes.add("currentPlayer");
      if ((initBits & INIT_BIT_GAME_PHASE) != 0) attributes.add("gamePhase");
      if ((initBits & INIT_BIT_TIME_LEFT) != 0) attributes.add("timeLeft");
      if ((initBits & INIT_BIT_CURRENT_VOTE) != 0) attributes.add("currentVote");
      if ((initBits & INIT_BIT_GAME_ID) != 0) attributes.add("gameId");
      return "Cannot build GameState, some of required attributes are not set " + attributes;
    }
  }
}
