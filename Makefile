SRC=src
BIN=bin
MAIN=main

all: compile run

compile:
	mkdir -p $(BIN)
	javac -d $(BIN) $(SRC)/*.java

run: compile
	java -cp $(BIN) $(MAIN)

clean:
	rm -rf $(BIN)

rebuild: clean all

.PHONY: all compile run clean rebuild