plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.alura"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// Flyway precisa de JDBC para funcionar
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.springframework:spring-jdbc")

	// dotEnv
	implementation("io.github.cdimascio:dotenv-java:3.2.0")

	// Driver correto para R2DBC
	runtimeOnly("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")

	// Driver JDBC necessário para Flyway
	runtimeOnly("org.postgresql:postgresql:42.7.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("com.h2database:h2:2.3.232")
	testImplementation("io.r2dbc:r2dbc-h2:1.0.0.RELEASE")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
