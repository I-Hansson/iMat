package Cart;

import ProductCard.ProductCard;

public interface ICartItem {
    public void updateCartItem(CartItem e);
    public void populateDetailView(ProductCard p);
    public void addCart(ProductCard p);
    public void decCart(ProductCard p);

}
