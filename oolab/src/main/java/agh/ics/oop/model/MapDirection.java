package agh.ics.oop.model;

public enum MapDirection{
    NORTH, SOUTH, WEST, EAST;

    @Override
    public String toString() {
        return switch(this) {
            case EAST ->"Wschód";
            case WEST -> "Zachód";
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            default -> throw new IllegalArgumentException("Nieznany kierunek");
        };


    }
    public MapDirection next(){
        return switch(this) {
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case NORTH ->EAST;
            default -> throw new IllegalArgumentException("Nieznany kierunek");
        };
    }
    public MapDirection previous(){
        return switch(this){
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case NORTH -> WEST;
            case SOUTH -> EAST;
            default -> throw new IllegalArgumentException("Nieznany kierunek");
        };
    }
    public Vector2d toUnitVector(){
     return switch(this){
         case NORTH -> new Vector2d(0,1);
         case SOUTH -> new Vector2d(0,-1);
         case EAST -> new Vector2d(1,0);
         case WEST -> new Vector2d(-1,0);
         default -> throw new IllegalArgumentException("Nieznany kierunek");
     };
    }
}