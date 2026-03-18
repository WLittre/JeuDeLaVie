SRC=src
BIN=bin
MAIN=Main

all: compile

compile:
	mkdir -p $(BIN)
	javac -d $(BIN) $(SRC)/**/*.java $(SRC)/*.java

run: compile
	java -cp $(BIN) $(MAIN)

clean:
	rm -rf $(BIN)

rebuild: clean all

.PHONY: all compile run clean rebuild