#PROTOTYPE

##DEFINITION
>Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype. (GoF)

In other words a class needs to explicitly support being copied or cloned. Other classes can then use the "prototype" as a basis for creating new instances of that class in a certain state.

>**Applicability**
> * when the classes to instantiate are specified at-runtime.
> * to avoid building a class hierarchy of factories.
> * when it might be more convenient to clone a class rather than instantiating it and set the desired state manually.

##EXPLANATION
You have probably been in the situation where you need a copy of a certain class. Perhaps you have an enemy in a game, and you need new instances of a certain enemy throughout the game. Imagine that level 1 in your game spawns new enemy every 2 minutes.
What you might do is to define a method in your level1 class that is called *getAxeMonster()*.

    class Level1
    {
        ...

        private Monster getAxeMonster()
        {
            Monster newAxeMonster = new Monster();  //Create a new instance
            newAxeMonster.setWeapon(AXE);           //Set the class state
            return newAxeMonster();
        }
    }

But perhaps we think that it should be the Monster class's responsibility to replicate itself so we might define a method in the class Monster called clone() which takes care of that.

    class Monster
    {
        ...

        public Monster clone()
        {
            Monster monsterCopy = new Monster;         //Create new instance
            monsterCopy.setWeapon( this.getWeapon() )  //Set the class state
            return monsterCopy;
        }
    }

And now the level1 class becomes:

    class Level1
    {
        private Monster axeMonsterPrototype = null; //The prototype 

        private Level1()
        {
            //Setup the default monster for this level
            axeMonsterPrototype = new Monster();  //Create a "prototype" for a monster
            axeMonsterPrototype.setWeapon(AXE);
        }

        public Monster getAxeMonster()
        {
            return axeMonsterPrototype.clone();  //Clone the "prototype"
        }
    }


What we have done here is remove the responsibility to replicate a class from the actor to the class itself. This lowers coupling, since other classes don't need to be concerned with internal workings of the class they are copying, and increases cohesion.
Now you could say that perhaps in C++ you could have used a copy constructor or in JAVA serializable, and in most cases it would be fine. However explicit clone method signals that this class IS clonable and some one has thought this through (hopefully).
Another benefit is that by handing the cloning in the same class you can access private and protected member variables, which otherwise you might have needed to expose to the outside world.

Now lets add a shield to the monster class. Would you rather search your code base and update all copying methods to also copy the shield or would you like to only update the monster clone method?

Explicit clone method is also valuable if getting the class instance into a certain state is expensive, e.g. if you had a class representing an image from the internet then

    InternetImage img = new InternetImage("www.cnn.com/frontpage.jpg");

This would mean that each time you needed a copy of that class you would need to download the image again instead of just copying the image data in memory.

###Factory with prototype
The GoF example in the book shows how this pattern might by applied to a "dynamic" factory. So lets create a monster factory. The monster factory accepts a default level monster and a boss monster. Each level can then at initialization load the factory with the monsters for that level, instead of subclassing the monster factory (MonsterFactoryLevel1, MonsterFactoryLevel2, etc.).

    class MonsterFactory
    {
        private Monster levelMonsterPrototype = null;
        private Monster bossMonsterPrototype = null;

        public MonsterFactory(Monster levelMonster, Monster bossMonster)
        {
            this.levelMonsterPrototype = levelMonster;
            this.bossMonsterPrototype = bossMonster;
        }

        public Monster getLevelMonster()
        {
            return this.levelMonsterPrototype.clone();
        }

        public Monster getLevelBoss()
        {
            return this.bossMonsterPrototype.clone();
        }
    }

