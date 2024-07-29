package io.github.caiobrunoE.quarkussocial.rest.config;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(title="Quarkus Social API",version = "1.0.1",
        contact = @Contact(
                name = "Caio Bruno E F de Oliveira",
                url = "https://github.com/CaioBrunoE",
                email = "caiobruno90@yahoo.com"),
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class SweggerConfig extends Application {


}
