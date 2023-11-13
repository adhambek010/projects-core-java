package text.based.adventure.game.app03;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			Random rand = new Random();

			String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assasin" };
			int maxEnemyHealth = 75;
			int enemyAttackDamage = 25;
			int plHealth = 100;
			int plAttack = 50;
			int upgradeHealPercentage = rand.nextInt(30,50);
			int healPotionsHealAmt = 3;
			int healPotionsDropChan = 50;
			boolean running = true;
			System.out.println("Welcome to the MyGame!");

			GAME: while (running) {
				System.out.println("-----------------------------------------");
				int enHealth = rand.nextInt(maxEnemyHealth);
				String enemy = enemies[rand.nextInt(enemies.length)];
				System.out.println("\t# " + enemy + " has appeared! #\n");

				while (enHealth > 0) {
					System.out.println("\tYour HP " + plHealth);
					System.out.println("\t" + enemy + "'s HP " + enHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t1. Attack");
					System.out.println("\t2. Drink health potion");
					System.out.println("\t3. Run!");
					String input = sc.nextLine();
					if (input.equals("1")) {
						int damageEn = rand.nextInt(plAttack);
						int damageTaken = rand.nextInt(enemyAttackDamage);
						enHealth -= damageEn;
						plHealth -= damageTaken;
						System.out.println("\t-> You strike the " + enemy + " for " + damageEn + " damage.");
						System.out.println("\t-> You recive " + damageTaken + " in retaliation!");
						if (plHealth < 1) {
							System.out.println("\t-> You taken too much damage, you are too weak to go on!\n");
							break;
						}
					} else if (input.equals("2")) {
						if (healPotionsHealAmt > 0) {
							plHealth += upgradeHealPercentage;
							healPotionsHealAmt -= 1;
							System.out.println("\t-> You drink health potion, healing yourself for "
									+ upgradeHealPercentage + "." + "\n\t-> You now have " + plHealth + " HP."
									+ "\n\t-> You now have " + healPotionsHealAmt + " health pitons left.\n");
							upgradeHealPercentage = rand.nextInt(30,50);
						} else {
							System.out.println(
									"\t-> You do not have any health potions left!\n\t-> Defeat enemies for a chance get one !");
						}
					} else if (input.equals("3")) {
						System.out.println("\t-> You run away from the " + enemy);
						continue GAME;
					} else {
						System.out.println("\tInvalid Input");

					}
				}

				if (plHealth < 1) {
					System.out.println("\t-> You limp out of the MyGame, weak from battle");
					break;
				}
				System.out.println("-----------------------------------------");
				System.out.println(" # " + enemy + " was defeated! #");
				System.out.println(" # You have " + plHealth + " HP left. #");
				if (rand.nextInt(100) < healPotionsDropChan) {
					healPotionsHealAmt++;
					System.out.println(" # The " + enemy + " dropped a health potion! #");
					System.out.println(" # You have " + healPotionsHealAmt + " health potion(s)! #");
				}
				System.out.println("-----------------------------------------");
				System.out.println("What would you like to do now?");
				System.out.println("1. Continue fighting");
				System.out.println("2. Exit Dungeon");
				String input = sc.nextLine();
				while (!input.equals("1") && !input.equals("2")) {
					System.out.println("Invalid Input\tPlease try again : ");
					input = sc.nextLine();

				}
				if (input.equals("1")) {
					System.out.println("You will continue your Adventure");

				} else if (input.equals("2")) {
					System.out.println("You exit the Dungeon, Succesful from your Adventure!");

				}

			}
		}

		System.out.println("\n###################\n");
		System.out.println("#THANKS FOR PLAYING!#");
		System.exit(0);

	}

}
