package nl.han.ica.MaffiaTrap.entities;

/**
 * IPlayer.java
 * Interface waar de functies voor een willekeurige power-up zitten die de speler van een power-up voorziet.
 * @author Vu Le
 */

public interface IPlayer {

    /**
     * Voeg extra leven toe aan speler (zie ExtraLife.java)
     */

    void addExtraLife();

    /**
     * Verlaag extra leven van de speler (zie ExtraLife.java)
     */

    void countOffExtraLife();

    /**
     * Maak de vuurbal aan (zie Pistol.java)
     */

    void makeFireball();
}
