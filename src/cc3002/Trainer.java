package cc3002;

import cc3002.pokemon.IPokemon;
import cc3002.visitor.PlayCardVisitor;

import java.util.ArrayList;

/**
 * Create a trainer entity
 *
 * @author cmesiasd
 * @version 1.0
 */
public class Trainer {
    private String name;
    private ArrayList<ICard> hand;
    private ArrayList<IPokemon> bench;
    private IPokemon activePokemon;
    private ArrayList<ICard> deck;
    private ArrayList<ICard> discardPile;
    private ICard[] sixPrize;


    /**
     * Constructor for Trainer
     *
     * @param name Trainer's name
     */
    public Trainer(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.activePokemon = null;
        this.bench = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.sixPrize = new ICard[6];

    }

    //region Properties

    /**
     * @return Trainer's bench
     */
    public ArrayList<IPokemon> getBench() {
        return bench;
    }


    /**
     * @return Pokemon's Active Pokemon
     */
    public IPokemon getActivePokemon(){
        return activePokemon;
    }

    public void setActivePokemon(IPokemon activePokemon) {
        this.activePokemon = activePokemon;
    }

    /**
     * @return Trainer's Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Trainer's hand
     */
    public ArrayList<ICard> getHand() {
        return hand;
    }

    public ArrayList<ICard> getDeck() {
        return deck;
    }

    public ArrayList<ICard> getDiscardPile() {
        return discardPile;
    }

    public ICard[] getSixPrize() {
        return sixPrize;
    }

    //endregion

    /**
     * Add a cart to the trainer's hand.
     * @param aCard the card to add in the hand.
     */
    void addCardToHand(ICard aCard) {
        hand.add(aCard);
    }


    /**
     * Trainer to play a card.
     * @param aCard A card that wants play.
     */
    void play(ICard aCard) {
        hand.remove(aCard);
        aCard.setTrainer(this);
        PlayCardVisitor v = new PlayCardVisitor();
        aCard.acceptVisitor(v);
    }

    /** Trainer attacks another trainer with
     *  active Pokemon's trainer.
     *
     * @param trainer Target trainer
     * @param index_at Index to select attack
     */
    public void attackTrainer(Trainer trainer, int index_at){
        trainer.receiveAnAttack(this,index_at);
    }

    public void receiveAnAttack(Trainer other, int index_at) {
        other.getActivePokemon().attack(this.getActivePokemon(), index_at);
        if (!this.activePokemon.isAlive()) {
            activePokemon = bench.get(0);
            bench.remove(0);
        }
    }

    public void evolutionPokemon(IPokemon oldPokemon, IPokemon newPokemon){
        if(oldPokemon.getID() == 1) {
            newPokemon.setCountEnergy(oldPokemon.getCountEnergy());
            oldPokemon.discard(this);
        }
    }


}
