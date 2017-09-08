package pl.mateuszkraska.gameoflife_livewallpaper.game;

class Person {

    private final Coordinates coordinates;
    private boolean isLive;
    private boolean isLock;

    public Person( Coordinates coordinates , boolean isLive ){
        this.coordinates = coordinates;
        this.isLive = isLive;
    }

    public int countOfNeighbor( Person[][] persons ){
        int countOfNeighbor = 0;
        if( coordinates.getX() != 0 ){
            if(persons[coordinates.getX()-1][coordinates.getY()].isLive){
                countOfNeighbor++;
            }
        }
        if( coordinates.getY() != 0 ){
            if(persons[coordinates.getX()][coordinates.getY()-1].isLive){
                countOfNeighbor++;
            }
        }
        if( coordinates.getX() != persons.length-1){
            if(persons[coordinates.getX()+1][coordinates.getY()].isLive){
                countOfNeighbor++;
            }
        }
        if( coordinates.getY() != persons[0].length-1 ){
            if(persons[coordinates.getX()][coordinates.getY()+1].isLive){
                countOfNeighbor++;
            }
        }
        if( coordinates.getX() != 0 && coordinates.getY() != 0 ){
            if(persons[coordinates.getX()-1][coordinates.getY()-1].isLive){
                countOfNeighbor++;
            }
        }
        if( coordinates.getX() != 0 && coordinates.getY() != persons[0].length-1 ){
            if(persons[coordinates.getX()-1][coordinates.getY()+1].isLive){
                countOfNeighbor++;
            }
        }
        if( coordinates.getX() != persons.length-1 && coordinates.getY() != 0 ){
            if(persons[coordinates.getX()+1][coordinates.getY()-1].isLive){
                countOfNeighbor++;
            }
        }
        if( coordinates.getX() != persons.length-1 && coordinates.getY() != persons[0].length-1 ){
            if(persons[coordinates.getX()+1][coordinates.getY()+1].isLive){
                countOfNeighbor++;
            }
        }
        return countOfNeighbor;
    }

    void die(){
        isLive = false;
    }

    void live(){
        isLive = true;
    }

    void lock(){
        isLock = true;
    }

    void unlock(){
        isLock = false;
    }

    boolean isLive() {
        return isLive;
    }
    boolean isLock(){return isLock;}
}
