package gameInfo;

public enum KeyboardLayout {

    WSAD(1),
    DEFAULT(0);


    private int id;
    private KeyboardLayout(int id){
        this.id = id;
    }


    public int getId(){
        return this.id;
    }

}
