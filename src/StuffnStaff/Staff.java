package StuffnStaff;

public class Staff implements Working{
    String name;
    int salary;
    String position;

    public Staff(String name, int salary, String position){
        setName(name);
        setSalary(salary);
        setPosition(position);
    }

    public void setName(String name){
        if(name==null || name.length()<=3){
            throw new IllegalArgumentException("Name cannot be empty or less than 3 characters");
        }
        this.name=name;
    }

    public void setSalary(int salary){
        if(salary<=0){
            throw new IllegalArgumentException("Salary cannot equal 0 or be negative");
        }
        this.salary=salary;
    }

    public void setPosition(String position){
        if(position==null){
            throw new IllegalArgumentException("You have to put something into position field");
        }
        this.position=position;
    }

    public String getName(){return name;}
    public int getSalary(){return salary;}
    public String getPosition(){return position;}

    public void work(){
        System.out.println(name + "is working as" + position);
    }

    public void fire(){
        System.out.println("Firing" + name + "," + position + "...");
        System.out.println("Matching taxes and salary");
        System.out.println("Throwing out...");
        System.out.println("Success" + name + "was fired without any chance of returning" );
    }
}
