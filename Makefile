GREEN=\033[0;32m
YELLOW=\033[0;33m
RED=\033[0;31m
RESET=\033[0m

GRADLE=./gradlew

.PHONY: build publish publish-local clean refresh validate help

build:
	@echo "$(YELLOW)Building the project...$(RESET)"
	$(GRADLE) build
	@echo "$(GREEN)Build completed successfully!$(RESET)"

publish: validate
	@echo "$(YELLOW)Publishing artifact to the defined repository...$(RESET)"
	$(GRADLE) publish
	@echo "$(GREEN)Artifact published successfully!$(RESET)"

publish-local: validate
	@echo "$(YELLOW)Publishing artifact to Maven local...$(RESET)"
	$(GRADLE) publishToMavenLocal
	@echo "$(GREEN)Artifact published to Maven local successfully!$(RESET)"

clean:
	@echo "$(YELLOW)Cleaning the build environment...$(RESET)"
	$(GRADLE) clean
	@echo "$(GREEN)Clean completed successfully!$(RESET)"

refresh:
	@echo "$(YELLOW)Refreshing dependencies and rebuilding the project...$(RESET)"
	$(GRADLE) build --refresh-dependencies
	@echo "$(GREEN)Dependencies refreshed and build completed successfully!$(RESET)"

validate:
	@echo "$(YELLOW)Validating the project...$(RESET)"
	$(GRADLE) check
	@echo "$(GREEN)Validation completed successfully!$(RESET)"

help:
	@echo "$(YELLOW)Available commands:$(RESET)"
	@echo "$(GREEN)  make build$(RESET)           - Build the project (tests are skipped)"
	@echo "$(GREEN)  make publish$(RESET)         - Publish the artifact to the defined repository"
	@echo "$(GREEN)  make publish-local$(RESET)   - Publish the artifact to Maven local (~/.m2/repository)"
	@echo "$(GREEN)  make clean$(RESET)           - Clean the build environment"
	@echo "$(GREEN)  make refresh$(RESET)         - Rebuild the project with refreshed dependencies"
	@echo "$(GREEN)  make validate$(RESET)        - Validate the project (run checks and tests)"
