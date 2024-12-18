# Makefile para manejar tareas comunes del proyecto con Gradle

# Variables de color para mensajes
GREEN=\033[0;32m
YELLOW=\033[0;33m
RED=\033[0;31m
RESET=\033[0m

# Alias para el comando de Gradle
GRADLE=./gradlew

.PHONY: build publish publish-local clean refresh validate help

# Construye el proyecto, omitiendo los tests
build:
	@echo "$(YELLOW)Building the project...$(RESET)"
	$(GRADLE) build
	@echo "$(GREEN)Build completed successfully!$(RESET)"

# Publica el JAR en el repositorio definido en build.gradle
publish: validate
	@echo "$(YELLOW)Publishing artifact to the defined repository...$(RESET)"
	$(GRADLE) publish
	@echo "$(GREEN)Artifact published successfully!$(RESET)"

# Publica el JAR en el repositorio Maven local (~/.m2/repository)
publish-local: validate
	@echo "$(YELLOW)Publishing artifact to Maven local...$(RESET)"
	$(GRADLE) publishToMavenLocal
	@echo "$(GREEN)Artifact published to Maven local successfully!$(RESET)"

# Limpia los artefactos generados
clean:
	@echo "$(YELLOW)Cleaning the build environment...$(RESET)"
	$(GRADLE) clean
	@echo "$(GREEN)Clean completed successfully!$(RESET)"

# Reconstruye el proyecto y actualiza las dependencias
refresh:
	@echo "$(YELLOW)Refreshing dependencies and rebuilding the project...$(RESET)"
	$(GRADLE) build --refresh-dependencies
	@echo "$(GREEN)Dependencies refreshed and build completed successfully!$(RESET)"

# Valida el proyecto ejecutando las tareas de verificación (tests y chequeos)
validate:
	@echo "$(YELLOW)Validating the project...$(RESET)"
	$(GRADLE) check
	@echo "$(GREEN)Validation completed successfully!$(RESET)"

# Ayuda para mostrar las opciones disponibles
help:
	@echo "$(YELLOW)Available commands:$(RESET)"
	@echo "$(GREEN)  make build$(RESET)           - Build the project (tests are skipped)"
	@echo "$(GREEN)  make publish$(RESET)         - Publish the artifact to the defined repository"
	@echo "$(GREEN)  make publish-local$(RESET)   - Publish the artifact to Maven local (~/.m2/repository)"
	@echo "$(GREEN)  make clean$(RESET)           - Clean the build environment"
	@echo "$(GREEN)  make refresh$(RESET)         - Rebuild the project with refreshed dependencies"
	@echo "$(GREEN)  make validate$(RESET)        - Validate the project (run checks and tests)"
