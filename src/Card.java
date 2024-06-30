import java.util.Random;

public class Card {

    int health;
    static int damage;
    String name;

    public String[] CARD_NAMES =
            {"\n ─────▄████▀█▄\n" +
            "───▄█████████████████▄  Blue-Eyes White Dragon\n" +
            "─▄█████.▼.▼.▼.▼.▼.▼▼▼▼\n" +
            "▄███████▄.▲.▲▲▲▲▲▲▲▲ attack:\n"  +
            "████████████████████▀▀ " + damage, "Skull Dragon", "Celtic Guardian", "Clown Zombie", "Cyber Commander", "Dark Chimera" , "Dark Magician", "Dark-Eyes Illusionist", "Dragon Master Knight", "Flame Swordsman"  /*,"Gaia the Dragon Champion", "Gaia The Fierce Knight", "Goblin Attack Force", "Harpie Lady", "Harpie's Pet Dragon", "Kuriboh", "Mystical Elf", "Red-Eyes BLack Dragon", "Red-Eyes Black Metal Dragon", "Saggi the Dark Clown", "Summoned Skull", "Thousand Dragon", "Time Wazard", "Toon Summoned Skull", "Two-Headed King Rex", "Zoa"*/};
 String test="-=-       \n" +
         "(\\  _  /)    \n" +
         "( \\( )/ )    \n" +
         "(       )    \n" +
         " `>   <'     \n" +
         " /     \\  hjw\n" +
         " `-._.-' " +
         "";

    public Card() {
        Random random = new Random();
        this.damage = (int) (Math.random()*500);
        this.name = CARD_NAMES[random.nextInt(CARD_NAMES.length)]; // Randomly select a card name from the array

    }


    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}

