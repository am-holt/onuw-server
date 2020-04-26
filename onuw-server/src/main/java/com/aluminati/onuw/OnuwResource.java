package com.aluminati.onuw;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class OnuwResource {

    private static GameStore gameStore = InMemGameStore.INSTANCE;

    @POST
    @Path("onuw/new")
    public CreateNewGameResponse createNewGame() {
        return CreateNewGameResponse.of(gameStore.createNewGame());
    }
}
