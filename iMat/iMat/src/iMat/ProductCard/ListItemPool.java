package ProductCard;

import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.HashMap;
import java.util.Map;

public class ListItemPool {

    private static IMatDataHandler handler = IMatDataHandler.getInstance();
    private static ListItemPool self;
    private Map<Product,ProductCard> listItems;

    private ListItemPool(){
        IMatDataHandler handler = IMatDataHandler.getInstance();
        listItems = new HashMap<>();

        for(ShoppingItem i: handler.getShoppingCart().getItems()) {
            listItems.put(i.getProduct(), new ProductCard(i));
        }

        for(Product p: handler.getProducts()) {
            if(!listItems.containsKey(p))
                listItems.put(p,new ProductCard(new ShoppingItem(p,0)));
        }
    }

    static public ListItemPool getInstance(){
        if(self == null)
            self = new ListItemPool();

        return self;
    }

    public ProductCard getProductCard(Product p){
        ProductCard item = listItems.get(p);
        item.update();
        return item;
    }

    static public void updateAfterOrder(){
        if(self == null)
            return;

        for(ShoppingItem p: handler.getShoppingCart().getItems())
            self.listItems.put(p.getProduct(),new ProductCard(new ShoppingItem(p.getProduct())));
    }
}