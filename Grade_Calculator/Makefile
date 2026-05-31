# Makefile for Grade Calculator Project

JC = javac
JFLAGS = -g -d bin -sourcepath src
SRC_DIR = src
BIN_DIR = bin

# Find all Java files in src/ and its subdirectories
SOURCES = $(wildcard $(SRC_DIR)/*.java $(SRC_DIR)/*/*.java)

default: classes

classes: $(SOURCES)
	@mkdir -p $(BIN_DIR)
	$(JC) $(JFLAGS) $(SOURCES)

run: classes
	java -cp $(BIN_DIR) Main

clean:
	$(RM) -r $(BIN_DIR)
	$(RM) -r reports/*
