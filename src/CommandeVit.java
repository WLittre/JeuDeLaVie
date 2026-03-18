public class CommandeVit extends Commande{
    public CommandeVit(Cellule c){
        cellule = c;
    }

    @Override
    public void executer(){
        cellule.vit();
    }
}
