## Student Information
 - Name: Heyang Yu
 - BUID: U26187790


## File Information
 - I've included the file information in the header of all java source file, so I skip this part


## Notes
 - Monsters and Heroes is a single player rpg game, player can create a team with multiple heros, explore the world,
 battle with monsters and level up, user can also purchase items from markets. All items purchased can be used by hero 
 who bought it.

 - Design Pattern Used: I used Factory pattern in this project, it decoupling user from the concrete classes, allow users
 to generate instance more easily, I used this for Hero, Monster and Market creation.


## Citations
 - https://chat.openai.com, I finished the Color class with the help of chatGPT.


## How to compile and run
 - Go to the project folder and open the terminal.
 - Run the following instructions:
 javac *.java
 java Main


## Input/Output Example
 - In this part, I will only demo part of the functionalities to avoid the out put be too long
 - basic inputs:
 <wasd>: move
 <h>: check hero stats
 <i>: check inventory and use items
 <m>: enter the market
 <r>: refresh the map or the markets
 <q>: quit/cancel/return
 <number>: select item or target.

(base) yuheyang@yuheyangdeMacBook-Air Asg4 % javac *.java
(base) yuheyang@yuheyangdeMacBook-Air Asg4 % java Main   
Which game do you want to play? 1: Monsters and Heros; Q: quit
Please input your choice: 1
Input an integer within 5-9 as the side length: 5
How many heros do you want in your party?
Input an integer within 1-3 as the number of heros: 1
Please select the type of your hero 1. 1: Warrior; 2: Sorcerer; 3: Paladin.
Input an integer within 1-3 as the type of hero: 1
Please select your hero 1.
No.   Name                 Mana       Strength   Agility    Dexterity  Starting Money Starting Exp
1     Gaerdal Ironhand     100        700        500        600        1354           7         
2     Sehanine Monnbow     600        700        800        500        2500           8         
3     Muamman Duathall     300        900        500        750        2546           6         
4     Flandal Steelskin    200        750        650        700        2500           7         
5     Undefeated Yoj       400        800        400        700        2500           7         
6     Eunoia Cyn           400        700        800        600        2500           6         
Input an integer within 1-6 as the hero you select: 1
+--+--+--+--+--+
|//|M |M |H |  |
+--+--+--+--+--+
|  |//|M |M |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |  |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: s
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |H |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |  |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: m
Welcome Gaerdal_Ironhand!
+----------------------------------+
Which action do you want to take?
1. Buying items
2. Selling items
q: quit
Your choice is: 1
1: Name: Thunder_Blast
        Type: Lightning Spell
        Price: 750
        Level: 4
        Damage: 950
        Mana Cost: 400

2: Name: Strength_Potion
        Type: Potion
        Price: 200
        Level: 1
        Increase: 75
        Attributes Affected: Strength

3: Name: Flame_Tornado
        Type: Fire Spell
        Price: 700
        Level: 4
        Damage: 850
        Mana Cost: 300

4: Name: Healing_Potion
        Type: Potion
        Price: 250
        Level: 1
        Increase: 100
        Attributes Affected: Health

5: Name: Platinum_Shield
        Type: Armor
        Price: 150
        Level: 1
        Damage Reduction: 200

6: Name: Thunder_Blast
        Type: Lightning Spell
        Price: 750
        Level: 4
        Damage: 950
        Mana Cost: 400

+----------------------------------+
Which action do you want to take?
1-6: Buy an item with the index
r: Refersh the item list, 100g required.
q: Finish buying
You have 1354 gold
Your choice is: 4
1: Name: Thunder_Blast
        Type: Lightning Spell
        Price: 750
        Level: 4
        Damage: 950
        Mana Cost: 400

2: Name: Strength_Potion
        Type: Potion
        Price: 200
        Level: 1
        Increase: 75
        Attributes Affected: Strength

3: Name: Flame_Tornado
        Type: Fire Spell
        Price: 700
        Level: 4
        Damage: 850
        Mana Cost: 300

4: Name: Platinum_Shield
        Type: Armor
        Price: 150
        Level: 1
        Damage Reduction: 200

5: Name: Thunder_Blast
        Type: Lightning Spell
        Price: 750
        Level: 4
        Damage: 950
        Mana Cost: 400

+----------------------------------+
Which action do you want to take?
1-5: Buy an item with the index
r: Refersh the item list, 100g required.
q: Finish buying
You have 1104 gold
Your choice is: q
+----------------------------------+
Which action do you want to take?
1. Buying items
2. Selling items
q: quit
Your choice is: q
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |H |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |  |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: s
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |M |  |
+--+--+--+--+--+
|//|  |M |H |M |
+--+--+--+--+--+
|  |  |  |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: s
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |M |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |  |H |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: a
Oops! You are unlucky!
Battle starts!
Monsters
+---------------------------------+
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 100
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Heros
+---------------------------------+
1: Name: Gaerdal_Ironhand
        Type: Warrior
        Level: 1
        Current Experience: 7
        HP: 100 / 100
        MP: 100 / 100
        Strength: 700
        Dexterity: 600
        Agility: 500
        Current Gold: 1104
+----------------------------+
Hero Gaerdal_Ironhand's turn!'
Please input your action
1-1: Attack the monster with corresponding index
c: cast a spell
p: use a potion
e: equit a weapon or armor
i: check stats
Your choice: 1
Gaerdal_Ironhand deals 30 damage to BigBad-Wolf
BigBad-Wolf deals 15 damage to Gaerdal_Ironhand
Monsters
+---------------------------------+
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 70
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Heros
+---------------------------------+
1: Name: Gaerdal_Ironhand
        Type: Warrior
        Level: 1
        Current Experience: 7
        HP: 95 / 100
        MP: 100 / 100
        Strength: 700
        Dexterity: 600
        Agility: 500
        Current Gold: 1104
+----------------------------+
Hero Gaerdal_Ironhand's turn!'
Please input your action
1-1: Attack the monster with corresponding index
c: cast a spell
p: use a potion
e: equit a weapon or armor
i: check stats
Your choice: 1
Gaerdal_Ironhand deals 30 damage to BigBad-Wolf
The attack from BigBad-Wolf to Gaerdal_Ironhand is missed!
Monsters
+---------------------------------+
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 40
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Heros
+---------------------------------+
1: Name: Gaerdal_Ironhand
        Type: Warrior
        Level: 1
        Current Experience: 7
        HP: 100 / 100
        MP: 100 / 100
        Strength: 700
        Dexterity: 600
        Agility: 500
        Current Gold: 1104
+----------------------------+
Hero Gaerdal_Ironhand's turn!'
Please input your action
1-1: Attack the monster with corresponding index
c: cast a spell
p: use a potion
e: equit a weapon or armor
i: check stats
Your choice: 1
Gaerdal_Ironhand deals 30 damage to BigBad-Wolf
BigBad-Wolf deals 15 damage to Gaerdal_Ironhand
Monsters
+---------------------------------+
1: Name: BigBad-Wolf
        Type: Exoskeleton
        Level: 1
        Health: 10
        Damage: 150
        Defense: 250
        Dodge Chance: 15%

Heros
+---------------------------------+
1: Name: Gaerdal_Ironhand
        Type: Warrior
        Level: 1
        Current Experience: 7
        HP: 95 / 100
        MP: 100 / 100
        Strength: 700
        Dexterity: 600
        Agility: 500
        Current Gold: 1104
+----------------------------+
Hero Gaerdal_Ironhand's turn!'
Please input your action
1-1: Attack the monster with corresponding index
c: cast a spell
p: use a potion
e: equit a weapon or armor
i: check stats
Your choice: 1
Gaerdal_Ironhand deals 30 damage to BigBad-Wolf
BigBad-Wolf dead!
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |M |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |H |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: h
1: Name: Gaerdal_Ironhand
        Type: Warrior
        Level: 1
        Current Experience: 9
        HP: 95 / 100
        MP: 100 / 100
        Strength: 700
        Dexterity: 600
        Agility: 500
        Current Gold: 1204
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |M |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |H |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: i
1: Name: Healing_Potion
        Type: Potion
        Price: 250
        Level: 1
        Increase: 100
        Attributes Affected: Health

+------------------------------+
Which action do you want to take?
1-1: Equip an equipment / Use a potion
q: Return
Your choice is: 1
You've used all items in your inventory!
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |M |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |H |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: h
1: Name: Gaerdal_Ironhand
        Type: Warrior
        Level: 1
        Current Experience: 9
        HP: 100 / 100
        MP: 100 / 100
        Strength: 700
        Dexterity: 600
        Agility: 500
        Current Gold: 1204
+--+--+--+--+--+
|//|M |M |  |  |
+--+--+--+--+--+
|  |//|M |M |  |
+--+--+--+--+--+
|//|  |M |  |M |
+--+--+--+--+--+
|  |  |H |  |  |
+--+--+--+--+--+
|//|M |//|  |  |
+--+--+--+--+--+
<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit
Please input your choice: 1
Invalid input! Please input your choice: q
You quited the game
Which game do you want to play? 1: Monsters and Heros; Q: quit
Please input your choice: q
Bye!
(base) yuheyang@yuheyangdeMacBook-Air Asg4 % z