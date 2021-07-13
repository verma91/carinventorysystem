package appRoot;

import dao.CarDao;
import entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CarInventorySystem {

    @Autowired
    private static CarDao carDao;
    private static int idCounter = 0;
    public static void main (String[] args) throws IOException {
       ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        carDao = (CarDao)context.getBean("carDao");
        carDao.setTemplate((JdbcTemplate)context.getBean("jdbcTemplate"));
        System.out.println("Welcome to Mullet Joe's Gently Used Autos!");
        System.out.println("Please enter command add , list or quit : ");
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        while(!command.equals("quit")){
            switch (command) {
                case "add":
                    String make,model;
                    int year;
                    float salesPrice;
                    System.out.println("Please enter car's make : ");
                    make = input.nextLine();
                    System.out.println("Please enter car's model : ");
                    model = input.nextLine();
                    System.out.println("Please enter car's year : ");
                    year = input.nextInt();
                    System.out.println("Please enter car's sales price : ");
                    salesPrice = input.nextFloat();
                    Car car = (Car) context.getBean("car");
                    List<Car> carListTemp = carDao.getCarList();
                    idCounter = carListTemp.size();
                    car.setMake(make);
                    car.setModel(model);
                    car.setSalesPrice(salesPrice);
                    car.setYear(year);
                    car.setId(++idCounter);
                    carDao.insertCar(car);
                    System.out.println("Please enter command add , list or quit : ");
                    command = input.nextLine();
                    break;
                case "list" :
                    List<Car> carList = carDao.getCarList();
                    float totalPrice = 0f;
                    for(Car e:carList){
                        System.out.println(e.getYear()+" "+e.getMake()+" "+e.getModel()+" "+e.getSalesPrice());
                        totalPrice +=e.getSalesPrice();
                    };
                    System.out.println("Number of cars : "+carList.size());
                    System.out.println("Total Inventory : $"+totalPrice);
                    System.out.println("Please enter command add , list or quit : ");
                    command = input.nextLine();
                    break;
                default:
                    System.out.println("Please enter correct command either add,list or quit : ");
                    command = input.nextLine();
            }
        }
        System.out.println("Good Bye!!");
    }
}
