/**
            * Implementing an interface Player1 to represent a 
            * player of the game Blackjack and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Player.java
            */

public interface Player1 
{
    void clearHand();
    String getName();
    void setName(String name);
    int getWinCount();
    void addWin();
    int getLostCount();
    void addLose();
    int getDrawCount();
    void addDraw();
    void resetStats();
    double getBank();
    void setBank(double bank);
    int getBoardPosition();
    void setBoardPosition(int boardPosition);
}
