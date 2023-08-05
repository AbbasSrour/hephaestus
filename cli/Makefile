help:
	@echo ''
	@echo 'Usage: make [TARGET] [EXTRA_ARGUMENTS]'
	@echo 'Targets:'
	@echo 'make dev: run the project in development mode'
	@echo 'make build: compile the project'

dev:
	fuser -k 8080/tcp || true
	~/.local/share/go/bin/air -c air.toml

build:
	go build -o bin/setup/main main.go