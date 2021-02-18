package rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Stateless
@Path("dates")
public class DateEndpoint {

    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate birthDateOwner = LocalDate.parse("1968.03.21", formatter);
        return Response.ok().entity(birthDateOwner).build();
    }

}
