SRC=src
BIN=bin
MAIN=Main

all: compile

compile:
	@if not exist $(BIN) mkdir $(BIN)
	javac -d $(BIN) $(SRC)/*.java $(SRC)/cellule/*.java $(SRC)/commande/*.java $(SRC)/etat/*.java $(SRC)/jeu/*.java $(SRC)/observateur/*.java $(SRC)/visiteur/*.java $(SRC)/json/*.java

run: compile
	java -cp $(BIN) $(MAIN)

clean:
	@if exist $(BIN) rmdir /S /Q $(BIN)

rebuild: clean all

.PHONY: all compile run clean rebuild	