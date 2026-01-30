package StuffnStaff;

public class Shorts extends Clothing{
    int waist;

    public Shorts(int id, int size, double price, String name, int waist ) {
        super(id, name, price, size);
        setWaist(waist);
    }

    public void setWaist(int waist){
        if(waist<=0){
            throw new IllegalArgumentException("Negarive wais?");
        }
        this.waist=waist;
    }

    public void putOn(){
        System.out.println("Putting this shorts on");
    }

    @Override
    public void cloth(){
        System.out.println("Can be put on for" + waist + "waist");
    }

    @Override
    public String getType(){
        return "Lower body";
    }

    @Override
    public String toString(){
        return super.toString() + waist;
    }

}
