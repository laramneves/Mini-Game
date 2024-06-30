public class AttackDone {
    private int damageDealt;
    private int opponentHealth;

    public AttackDone(int damageDealt, int opponentHealth) {
        this.damageDealt = damageDealt;
        this.opponentHealth = opponentHealth;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public int getOpponentHealth() {
        return opponentHealth;
    }
}
