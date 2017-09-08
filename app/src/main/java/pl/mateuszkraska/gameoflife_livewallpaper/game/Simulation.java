package pl.mateuszkraska.gameoflife_livewallpaper.game;

import java.util.Random;

import pl.mateuszkraska.gameoflife_livewallpaper.render.FieldState;

public class Simulation {

    private Person[][] persons;

    public Simulation( int sizeX , int sizeY ){
        persons = new Person[sizeX][sizeY];
        Random random = new Random();
        for( int x = 0 ; x < persons.length ; x++ ){
            for( int y = 0 ; y < persons[0].length ; y++ ){
                persons[x][y] = new Person(new Coordinates(x,y),random.nextBoolean());
            }
        }
    }

    public void makeNextGeneration(){

        int[][] numberOfNeighbors = new int[persons.length][persons[0].length];
        for( int x = 0 ; x < persons.length ; x++ ){
            for( int y = 0 ; y < persons[0].length ; y++ ){
                numberOfNeighbors[x][y] = persons[x][y].countOfNeighbor(persons);
            }
        }

        for( int x = 0 ; x < persons.length ; x++ ){
            for( int y = 0 ; y < persons[0].length ; y++ ){
                if( persons[x][y].isLock() ){
                    continue;
                }else if( !persons[x][y].isLive() && numberOfNeighbors[x][y] == 3 ){
                    persons[x][y].live();
                }else if( persons[x][y].isLive() && (numberOfNeighbors[x][y] == 2 || numberOfNeighbors[x][y] == 3) ){
                    persons[x][y].live();
                }else{
                    persons[x][y].die();
                }
            }
        }

    }

    public void makeFieldAliveAndLock( Coordinates coordinates ){
        persons[coordinates.getX()][coordinates.getY()].live();
        persons[coordinates.getX()][coordinates.getY()].lock();
    }
    public void unlockAll(){
        for( int x = 0 ; x < persons.length ; x++ ){
            for( int y = 0 ; y < persons[0].length ; y++ ){
                persons[x][y].unlock();
            }
        }
    }

    public FieldState[][] getFieldStates(){
        FieldState[][] fieldStates = new FieldState[persons.length][persons[0].length];
        for( int x = 0 ; x < persons.length ; x++ ){
            for( int y = 0 ; y < persons[0].length ; y++ ){
                fieldStates[x][y] = new FieldState(persons[x][y].isLive());
            }
        }
        return fieldStates;
    }

    @Override
    public String toString() {
        for( int x = 0 ; x < persons.length ; x++ ){
            for( int y = 0 ; y < persons[0].length ; y++ ){
                if( persons[x][y].isLive() ){
                    System.out.print("o");
                }else{
                    System.out.print("x");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
        return super.toString();
    }
}
