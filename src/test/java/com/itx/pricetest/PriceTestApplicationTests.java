package com.itx.pricetest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceTestApplicationTests {

    public static final String BASE_PACKAGE = "com.itx.pricetest";

    private static final String JAVA_STANDARD_LIBRARY = "java..";

    private static final String REACTOR_STANDARD_LIBRARY = "reactor..";

    private static final String JAKARTA_STANDARD_LIBRARY = "jakarta..";

    public static final String APPLICATION_PACKAGE = BASE_PACKAGE + ".application..";

    public static final String DOMAIN_PACKAGE = BASE_PACKAGE + ".domain..";

    private final JavaClasses importedClasses = new ClassFileImporter().importPackages(BASE_PACKAGE);

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        Assertions.assertTrue(context.getBeanDefinitionCount() > 0);
    }

    @Test
    void applicationLayerShouldDependOnDomainLayerAndJavaStandardLibrary() {
        ArchRule rule = noClasses().that()
                .resideInAPackage(APPLICATION_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(APPLICATION_PACKAGE, DOMAIN_PACKAGE, REACTOR_STANDARD_LIBRARY, JAVA_STANDARD_LIBRARY);
        rule.check(importedClasses);
    }

    @Test
    void domainLayerShouldDependOnJavaStandardLibrary() {
        ArchRule rule = noClasses().that()
                .resideInAPackage(DOMAIN_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(DOMAIN_PACKAGE, REACTOR_STANDARD_LIBRARY, JAKARTA_STANDARD_LIBRARY, JAVA_STANDARD_LIBRARY);
        rule.check(importedClasses);
    }

}