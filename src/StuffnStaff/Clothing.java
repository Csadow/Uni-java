package StuffnStaff;

public abstract class Clothing {
    protected int id;
    protected String name;
    protected double price;
    protected int size;

    public Clothing(int id, String name, double price, int size){
        setId(id);
        setName(name);
        setPrice(price);
        setSize(size);
    }

    public void setId(int id){
        if (id<=0){
            throw new IllegalArgumentException("Unuceptable id" + this.id);
        }
        this.id = id;

    }

    public void setName(String name){
        if(name==null){
            throw new IllegalArgumentException("You must enter something");
        }
        if(name.length()<2){
            throw new IllegalArgumentException("Name should at least have 3 characters");
        }
        this.name=name;
    }

    public void setPrice(double price){
        if(price<0){
            throw new IllegalArgumentException("The item should cost something");
        }
        this.price=price;
    }

    public void setSize(int size){
        if(size<=0){
            throw new IllegalArgumentException("Size cannot be 0 or negative");
        }
        this.size=size;
    }

    public abstract void cloth();
    public abstract String getType();

    public int getId(){return id;}
    public String getName(){return name;}
    public double getPrice(){return price;}
    public int getSize(){return size;}

    @Override
    public String toString(){
        return "[" + getType() + "]" + name + id + size + price;
    }

}
