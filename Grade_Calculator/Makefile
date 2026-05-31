# Makefile for Grade Calculator Project

JC = javac
JFLAGS = -g -d bin -sourcepath src
SRC_DIR = src
BIN_DIR = bin

# Find all Java files in src/ and its subdirectories
SOURCES = $(wildcard $(SRC_DIR)/*.java $(SRC_DIR)/*/*.java)

# Cross-platform compatibility for Windows and Linux/Mac
ifeq ($(OS),Windows_NT)
	MKDIR = if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	CLEAN_BIN = -rmdir /s /q $(BIN_DIR) 2>nul
	CLEAN_REPORTS = -del /q reports\* 2>nul
else
	MKDIR = mkdir -p $(BIN_DIR)
	CLEAN_BIN = -rm -rf $(BIN_DIR)
	CLEAN_REPORTS = -rm -rf reports/*
endif

default: classes

classes: $(SOURCES)
	@$(MKDIR)
	$(JC) $(JFLAGS) $(SOURCES)

run: classes
	java -cp $(BIN_DIR) Main

clean:
	@$(CLEAN_BIN)
	@$(CLEAN_REPORTS)
