package za.ac.tut.kotashop.global;

import za.ac.tut.kotashop.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    public static List<Product> cart;

    static {
        cart = new ArrayList<Product>();
    }


}
