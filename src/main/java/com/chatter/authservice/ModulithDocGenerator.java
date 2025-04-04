package com.chatter.authservice;

import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModulithDocGenerator {
    public static void main(String[] args) {
        var modules = ApplicationModules.of(AuthServiceApplication.class);
        new Documenter(modules)
                .writeDocumentation()
                .writeModulesAsPlantUml();
    }
}
