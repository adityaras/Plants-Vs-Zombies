package sample;

public class MyExceptions extends Exception
{

}
class GameWinnerException extends MyExceptions
{

}
class PlantDiedException extends MyExceptions
{

}

class ZombieKilledException extends MyExceptions {
    Zombies Zombie;
    ZombieKilledException(Zombies z){
        Zombie=z;
        //System.out.println("Zombie_Dead");
    }
}

class GamePausedException extends MyExceptions
{

}
