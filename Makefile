SRC = src
BIN = bin
MAIN = Main
RES = $(SRC)/json

ifeq ($(OS),Windows_NT)
	MKDIR = if not exist $(BIN) mkdir $(BIN)
	MKDIR_RES = if not exist $(BIN)/json mkdir $(BIN)/json
	COPY_RES = copy /Y $(subst /,\,$(RES)/structures.txt) $(subst /,\,$(BIN)/json/structures.txt) >nul
	RMDIR = if exist $(BIN) rmdir /S /Q $(BIN)
else
	MKDIR = mkdir -p $(BIN)
	MKDIR_RES = mkdir -p $(BIN)/json
	COPY_RES = cp $(RES)/structures.txt $(BIN)/json/structures.txt
	RMDIR = rm -rf $(BIN)
endif

all: compile

compile:
	@$(MKDIR)
	javac -d $(BIN) $(SRC)/*.java $(SRC)/cellule/*.java $(SRC)/commande/*.java $(SRC)/etat/*.java $(SRC)/jeu/*.java $(SRC)/observateur/*.java $(SRC)/visiteur/*.java $(SRC)/json/*.java
	@$(MKDIR_RES)
	@$(COPY_RES)

run: compile
	java -cp $(BIN) $(MAIN)

clean:
	@$(RMDIR)

rebuild: clean all

.PHONY: all compile run clean rebuild