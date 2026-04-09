# Git Commit Style Guide

## Format
```
type(scope): message
```
- **Type**: imperative (add, remove, fix, update, etc.)
- **Scope**: init, setup, repo, services, model, etc.
- **Message**: short summary

## Commit Types
| Type | Description |
|------|-------------|
| `chore` | Maintenance, dependencies, tooling |
| `feat` | New features or functionality |
| `refactor` | Code refactoring |
| `fix` | Bug fixes |
| `setup` | Initial setup, configuration |
| `config` | Configuration changes |
| `docs` | Documentation changes |
| `test` | Test-related changes |

## Detailed Message
Use `-m` flag for additional details:
```
git commit -m "type(scope): message" -m "detailed description"
```

## Examples
```
setup(root): add root .gitignore
Exclude .idea/ IDE files and target/ build directories from version control

feat(services): add restaurant-service
Add Spring Boot microservice for restaurant management with Maven wrapper

fix(model): fix User entity validation
Update email validation to accept longer domain names

refactor(repo): simplify database connection
Replace JDBC with JPA template for cleaner code

config(services): update port configurations
Change default ports to avoid conflicts with monolith app

chore(deps): update Spring Boot version
Upgrade to Spring Boot 3.2.0 for security patches

docs(readme): add API documentation
Document REST endpoints for customer-service

test(order): add order validation tests
Add unit tests for order total calculation
```

## Files to Ignore
- `.idea/` - IDE configuration
- `target/` - Build output directories
