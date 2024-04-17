## Student Information
 - Name: Heyang Yu
 - BUID: U26187790

 - Name: Nihal Poosa
 - BUID: U77692324


## File Information
 - We've included the file information in the header of all java source file, so this part is skipped


## Notes
 - Legends of valor is a multi-player(hero) moba game, the goal of hero team is to reach the nexus of monster team and 
 prevent their nexus to be accessed by monsters.

 - Design pattern we used in this assignment:
 1. Factory Pattern: We implemented factory pattern and use factories to generate heroes, monsters, markets and grids.
 2. Strategy Pattern: As potion and spell are both "usable" so we implemented strategy pattern to encapsulate the behavior
 "use" and create different subclass for it, then attatch different use behavior to different items.
 3. Observer Pattern: In this game hero gets buffed when locating on different terrians, so we implemented observer pattern
 here to make hero the observer and the map(game board) to be the subject, every time hero moves to a new gird in this game,
 the map will notify which grid hero is on, this can decouple the hero class and the map class.
 4. Singleton Pattern: For grid factory, we used singleton pattern to ensure only one instance is created.


## Citations
 - https://chat.openai.com, we finished the Color class with the help of chatGPT.


## How to compile and run
 - Go to the project folder and open the terminal.
 - Run the following instructions:
 javac *.java
 java Main


## Input/Output Example
 - In this part, we will only demo part of the functionalities to avoid the output to be too long
 - basic inputs:
 <wasd>: move
 <h>: check hero stats
 <p>: check and consume potions
 <m>: enter the market, only allowed when hero is in the nexus
 <r>: recall(go back to nexus), in the market menu, this can be used to refresh item list
 <q>: quit/cancel/return
 <c>: check weapon/armor list and change weapon/armor
 <g>: show the game info, including the rule of the game and the meaning of grids with different color.
 <j>: select a target monster in range and attack it using the weapon, you cannot attack without a weapon
 <k>: select a spell in inventory and select a monster in range to attack monster with this spell
 <t>: teleport to another lane, you can choose to teleport to the side of hero or the back of hero

In this demo, I first purchase and equip a weapon for each hero, then start moving forward as follows:

+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|H |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Casper
        Type: Spirit
        Level: 1
        Health: 100
        Damage: 100
        Defense: 100
        Dodge Chance: 50%

Input an integer within 1-1 as the target: 1
Gaerdal_Ironhand deals 45 damage to Casper
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 100
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Rillifane_Rallathil deals 72 damage to BigBad-Wolf
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Natsunomeryu
        Type: Dragon
        Level: 1
        Health: 100
        Damage: 100
        Defense: 200
        Dodge Chance: 10%

Input an integer within 1-1 as the target: 1
Parzival deals 73 damage to Natsunomeryu
Casper deals 10 damage to Gaerdal_Ironhand
BigBad-Wolf deals 15 damage to Rillifane_Rallathil
Natsunomeryu deals 10 damage to Parzival
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Casper
        Type: Spirit
        Level: 1
        Health: 55
        Damage: 100
        Defense: 100
        Dodge Chance: 50%

Input an integer within 1-1 as the target: 1
Oops! Casper dodged!
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 28
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Oops! BigBad-Wolf dodged!
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Natsunomeryu
        Type: Dragon
        Level: 1
        Health: 27
        Damage: 100
        Defense: 200
        Dodge Chance: 10%

Input an integer within 1-1 as the target: 1
Parzival deals 73 damage to Natsunomeryu
Monster Natsunomeryu is defeated!
Rillifane_Rallathil level up!
Casper deals 10 damage to Gaerdal_Ironhand
BigBad-Wolf deals 15 damage to Rillifane_Rallathil
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Casper
        Type: Spirit
        Level: 1
        Health: 55
        Damage: 100
        Defense: 100
        Dodge Chance: 50%

Input an integer within 1-1 as the target: 1
Gaerdal_Ironhand deals 45 damage to Casper
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H | M|  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 28
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Rillifane_Rallathil deals 74 damage to BigBad-Wolf
Monster BigBad-Wolf is defeated!
Gaerdal_Ironhand level up!
Parzival level up!
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
Casper deals 10 damage to Gaerdal_Ironhand
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Casper
        Type: Spirit
        Level: 1
        Health: 10
        Damage: 100
        Defense: 100
        Dodge Chance: 50%

Input an integer within 1-1 as the target: 1
Oops! Casper dodged!
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|H | M|  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
|H | M|  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
Casper deals 10 damage to Gaerdal_Ironhand
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Casper
        Type: Spirit
        Level: 1
        Health: 10
        Damage: 100
        Defense: 100
        Dodge Chance: 50%

Input an integer within 1-1 as the target: 1
Gaerdal_Ironhand deals 49 damage to Casper
Monster Casper is defeated!
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |  | M|
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 100
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Rillifane_Rallathil deals 74 damage to BigBad-Wolf
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  | M|  |  | M|  |H | M|
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Natsunomeryu
        Type: Dragon
        Level: 1
        Health: 100
        Damage: 100
        Defense: 200
        Dodge Chance: 10%

Input an integer within 1-1 as the target: 1
Parzival deals 77 damage to Natsunomeryu
Natsunomeryu deals 10 damage to Parzival
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H | M|
+--+--+--+--+--+--+--+--+
|  | M|  |H | M|  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 100
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Gaerdal_Ironhand deals 46 damage to BigBad-Wolf
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H | M|
+--+--+--+--+--+--+--+--+
|  | M|  |H | M|  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 26
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Oops! BigBad-Wolf dodged!
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H | M|
+--+--+--+--+--+--+--+--+
|  | M|  |H | M|  |  |  |
+--+--+--+--+--+--+--+--+
|H |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: Natsunomeryu
        Type: Dragon
        Level: 1
        Health: 23
        Damage: 100
        Defense: 200
        Dodge Chance: 10%

Input an integer within 1-1 as the target: 1
Parzival deals 77 damage to Natsunomeryu
Monster Natsunomeryu is defeated!
BigBad-Wolf deals 15 damage to Rillifane_Rallathil
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H | M|  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Gaerdal_Ironhand's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 54
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Oops! BigBad-Wolf dodged!
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H | M|  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Rillifane_Rallathil's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: j
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 26
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Input an integer within 1-1 as the target: 1
Rillifane_Rallathil deals 74 damage to BigBad-Wolf
Monster BigBad-Wolf is defeated!
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |H |  |
+--+--+--+--+--+--+--+--+
|  |  |  |H |  |  |  |  |
+--+--+--+--+--+--+--+--+
|H | M|  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
|  |  |  |  |  |  |  |  |
+--+--+--+--+--+--+--+--+
Hero Parzival's turn!
<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit
Your choice is: w
Congratulations! Hero team wins!
Which game do you want to play? 1: Monsters and Heros; 2: Legends of Valor; Q: quit
Please input your choice: q
Bye!
(base) yuheyang@crc-dot1x-nat-10-239-211-194 611assignment4 % 