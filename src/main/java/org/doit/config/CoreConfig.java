package org.doit.config;


import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"org.doit.service"})
public class CoreConfig {
}
