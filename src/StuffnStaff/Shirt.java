package StuffnStaff;

public class Shirt extends Clothing{
    private int sleeveLength;

    public Shirt(int id, String name, double price, int size, int sleeveLength){
        super(id, name, price, size);
        setSleeveLength(sleeveLength);
    }

    public void setSleeveLength(int sleeveLength){
        if(sleeveLength<=0){
            throw new IllegalArgumentException("Negative sleevses?");
        }
        this.sleeveLength=sleeveLength;
    }

    public void check(){
        System.out.println("This shirt seems to be in good shape");
    }

    @Override
    public void cloth(){
        System.out.println(name + "with" + sleeveLength + "sleeve length is being worn");
    }

    @Override
    public String getType(){
        return "Upper body";
    }

    @Override
    public String toString(){
        return super.toString() + sleeveLength;
    }

}
