package nl.han.ica.MaffiaTrap.entities;

/**
 * IPlayer.java
 * Interface waar de functies voor een willekeurige power-up zitten die de speler van een power-up voorziet.
 * @author Vu Le
 */

public interface IPlayer {

    void addExtraLife();

    void countOffExtraLife();

    void makeFireball();
}
