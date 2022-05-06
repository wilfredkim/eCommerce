package com.ecommerce.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "eCommerce EndPoints",
                description = "",
                contact = @Contact(name = "",
                        url = "",
                        email = ""
                ),
                license = @License(
                        name = "Licence",
                        url = "https://github.com/wilfredkim")),
        servers = @Server(url = "http://localhost:9000")
)
public class OpenAPIConfiguration {
}
