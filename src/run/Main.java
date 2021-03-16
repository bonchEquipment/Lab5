package run;

import collection.*;

import comands.Command;
import utility.Console;
import utility.FactoryOfCommands;

import java.util.Collection;

public class Main {
    public static void main(String[] args) throws Exception {

         Console console = new Console();
        console.run();

    }
}

//[{"id":11,"name":"moto-moto","coordinates":{"x":110.0,"y":115.0},"creationDate":"2021-03-14T19:17:18.0853793+03:00[Europe/Moscow]","enginePower":53.5,"type":"MOTORCYCLE","fuelType":"DIESEL"},{"id":12,"name":"rocket","coordinates":{"x":20.0,"y":15.6},"creationDate":"2021-03-14T19:17:18.1010084+03:00[Europe/Moscow]","enginePower":680.1,"type":"SPACESHIP","fuelType":"ANTIMATTER"},{"id":13,"name":"vertofly","coordinates":{"x":1000.0,"y":5000.0},"creationDate":"2021-03-14T19:17:18.1010084+03:00[Europe/Moscow]","enginePower":400.0,"type":"CHOPPER","fuelType":"MANPOWER"}]
