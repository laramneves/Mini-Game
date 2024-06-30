enum CardType {
    ATTACKING,
    POWER_UP
}

public class CardFactory {
    public static Card createCard(CardType type, String name, int health, int damage) {
        switch (type) {
            case ATTACKING:
                return new AttackingCard();
            case POWER_UP:
                return new PowerUpCard(name, health, damage);
            default:
                throw new IllegalArgumentException("Invalid card type");
        }
    }

    public static Card createRandomCard(String name, int health, int damage) {
        if (Math.random() < 0.7) {
            return createCard(CardType.ATTACKING, name, health, damage);
        } else {
            return createCard(CardType.POWER_UP, name, health, damage);
        }
    }
}


class AttackingCard extends Card {
    public AttackingCard() {
        super();
    }
}

class PowerUpCard extends Card {
    public PowerUpCard(String name, int health, int damage) {
        super();
    }
}


